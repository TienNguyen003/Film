'use strict';

console.warn("Đăng nhập 2 lớp, Làm chức năng bài viết, đề xuất, có thể bạn cũng thích, chức năng về tài khoản(tăng tu vi-nạp tiền tăng tu vi???, bxh, góp ý, chatroom, đổi mật khẩu(xác nhận qua eamil), Phản hồi bình luận, Mua bảo vật)");

(function($) {

	/*------------------
		Preloader
	--------------------*/
	$(window).on('load', function() {
		$(".loader").fadeOut();
		$("#preloder").delay(200).fadeOut("slow");

		/*------------------
			FIlter
		--------------------*/
		$('.filter__controls li').on('click', function() {
			$('.filter__controls li').removeClass('active');
			$(this).addClass('active');
		});
		if ($('.filter__gallery').length > 0) {
			var containerEl = document.querySelector('.filter__gallery');
			var mixer = mixitup(containerEl);
		}
	});

	/*------------------
		Background Set
	--------------------*/
	$('.set-bg').each(function() {
		var bg = $(this).data('setbg');
		$(this).css('background-image', 'url(' + bg + ')');
	});

	// Search model
	$('.search-switch').on('click', function() {
		$('.search-model').fadeIn(400);
	});

	$('.search-close-switch').on('click', function() {
		$('.search-model').fadeOut(400, function() {
			$('#search-input').val('');
		});
	});

	/*------------------
		Navigation
	--------------------*/
	$(".mobile-menu").slicknav({
		prependTo: '#mobile-menu-wrap',
		allowParentLinks: true
	});

	/*------------------
		Hero Slider
	--------------------*/
	var hero_s = $(".hero__slider");
	hero_s.owlCarousel({
		loop: true,
		margin: 0,
		items: 1,
		dots: true,
		nav: true,
		navText: ["<span class='arrow_carrot-left'></span>", "<span class='arrow_carrot-right'></span>"],
		animateOut: 'fadeOut',
		animateIn: 'fadeIn',
		smartSpeed: 1200,
		autoHeight: false,
		autoplay: true,
		mouseDrag: false
	});

	/*------------------
		Video Player
	--------------------*/
	const player = new Plyr('#player', {
		controls: ['play-large', 'play', 'progress', 'current-time', 'mute', 'captions', 'settings', 'fullscreen'],
		seekTime: 25
	});

	/*------------------
		Niceselect
	--------------------*/
	$('select').niceSelect();

	/*------------------
		Scroll To Top
	--------------------*/
	$("#scrollToTopButton").click(function() {
		$("html, body").animate({ scrollTop: 0 }, "slow");
		return false;
	});

	/*------------------
		F12
	--------------------*/
	document.addEventListener('keydown', function(e) {
		if (e.key === 'F12' || (e.ctrlKey && e.shiftKey && e.key === 'I')) {
			window.location.href = 'https://www.tiktok.com/@candyy1703';
		}
	});


})(jQuery);


let idUserActivity = document.querySelector(".idUserActivity");
if (idUserActivity != null) {
	const TIMEOUT_INACTIVE = 600000;
	let lastActivityTime = Date.now();
	let timeOut;
	let isCheckingTimeout = false;

	function updateLastActivityTime() {
		lastActivityTime = Date.now();
		startTimeout();
	}

	function checkSessionTimeout() {
		if (isCheckingTimeout) {
			return;
		}
		isCheckingTimeout = true;
		const currentTime = Date.now();
		const timeSinceLastActivity = currentTime - lastActivityTime;

		const idUser = document.querySelector(".idUserLogin");
		if (idUser != null) {
			fetch('/updateIsActivity', {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json'
				},
				body: JSON.stringify({ id: parseInt(idUser.innerHTML), notifical: timeSinceLastActivity >= TIMEOUT_INACTIVE ? 0 : 1 })
			});
		}
	}


	function startTimeout() {
		// Xóa bỏ setTimeout hiện tại (nếu có)
		clearTimeout(timeOut);
		// Khởi tạo setTimeout mới
		timeOut = setTimeout(checkSessionTimeout, TIMEOUT_INACTIVE);
	}

	// Kiểm tra và bắt đầu setTimeout ban đầu
	if (idUserActivity.innerHTML == 1) {
		startTimeout();
	}

	// Cập nhật thời gian lần cuối hoạt động khi có hành động của người dùng
	document.addEventListener('mousemove', updateLastActivityTime);
	document.addEventListener('mousedown', updateLastActivityTime);
	document.addEventListener('keypress', updateLastActivityTime);
}