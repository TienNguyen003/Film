'use strict';

// fetch api comment
let currentURLS = window.location.href;
let slugFilmS = "";
if (currentURLS.includes("detail-film")) {
	slugFilmS = currentURLS.replace("http://localhost:8081/detail-film?slug=", "");
} else if (currentURLS.includes("watch-film")) {
	slugFilmS = (currentURLS.substring((currentURLS.indexOf("?slug=") + 6), currentURLS.indexOf("&episodes=")));
}

const comments = document.querySelector(".comments");
if (comments != null) {
	const idUserCmt = document.querySelector(".idUserCmt");
	let idUser = -1;
	if(idUserCmt != null) idUser = parseInt(idUserCmt.innerHTML);
	fetch(`http://localhost:8081/api/comment/${slugFilmS}`)
		.then(res => res.json())
		.then((data) => {
			let html = "";
			data.map(item => {
				html += `<div class="anime__details__review">
                                <div class="anime__review__item">
                                    <div class="anime__review__item__pic">
                                        <img src=${item.image} alt="">
                                        <img src="img/avatar/khung.gif" alt="" class="frame_avatar">
                                    </div>
                                    <div class="anime__review__item__text">
                                        <div class="comment_header">
                                            <div class="comment_author">
                                                <span class="pbbm" data-tooltip="Đạo Tông"><img
                                                        src="https://hoathinh3d.tube/wp-content/uploads/2024/02/dao-tong.gif"
                                                        class="pbbm" alt="Đạo Tông" width="30px"></span>
                                                ${item.name}
                                                <span class="clan" data-tooltip="Đệ Tử">🏯Tiêu Dao⚔︎</span>
                                            </div>
                                            <div class="mycred-wrap">
                                                <img alt="" src="img/avatar/vo-thien-tan.webp">
                                            </div>
                                            <span class="displayTime" style="color: #fff"></span>
                                            <input value='${item.createAt}' type="hidden" class="hiddenTime">
                                        </div>
                                        <input class="contentComment" value="${item.content}" readonly>
                                        <div class="updateCm"><button type="submit">Lưu</button></div>
                                        ${item.idUser == idUser ? `<span class="settingComment"><i class="fa fa-cog"></i><ul class="list-group listSettingCm">  
                                        	<li class="list-group-item clickEditComment" id="${item.id}">Chỉnh sửa</li>
                                        	<li class="list-group-item">Xóa</li></ul></span>` : ''}										
                                    </div>
                                </div>
                            </div>`
			})
			if (html == "") html = "<h5 class='noComment' style='color: #ffffff;font-family: 'Oswald', sans-serif;'>Chưa có bình luận nào</5>";
			comments.innerHTML = html;
			const hiddenTime = document.querySelectorAll('.hiddenTime');
			const displayTime = document.querySelectorAll('.displayTime');
			for (let i = 0; i < hiddenTime.length; i++) {
				displayTime[i].innerHTML = tinhKhoangCach(chuyenDoiDinhDangThoiGian(hiddenTime[i].value), chuyenDoiThoiGian(new Date()));
			}
			
			const clickEditComment = document.querySelectorAll(".clickEditComment");
			for (let i = 0; i < clickEditComment.length; i++) {
				clickEditComment[i].onclick = () => {					
					const content = clickEditComment[i].parentElement.parentElement.parentElement;
					const contentComment = content.querySelector(".contentComment");
					contentComment.removeAttribute("readonly");
					contentComment.focus();
					const updateCm = content.querySelector(".updateCm");
					updateCm.style.display= "block";
					updateCm.onclick = () => {
						editComment(contentComment.value, slugFilmS, clickEditComment[i].getAttribute("id"));
						updateCm.style.display= "none";
						contentComment.readOnly = true;
						const html = `<div style="color: #fff; font-size: 10px"><i class="fa fa-pencil-square-o"></i> Đã chỉnh sửa</div>`;
						content.insertAdjacentHTML('beforeend', html);
					}
				}
			}
		})
		.catch(error => {
			console.error('Có lỗi xảy ra' + error);
		});
}

const formNoClick = document.querySelector(".formNoClick");
if (formNoClick != null) formNoClick.addEventListener("submit", function(event) { event.preventDefault(); });

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

// tính khoảng cách thời gian
function chuyenDoiThoiGian(thoiGian) {
	const date = new Date(thoiGian);

	// Lấy các thành phần thời gian
	const gio = String(date.getHours()).padStart(2, "0");
	const phut = String(date.getMinutes()).padStart(2, "0");
	const giay = String(date.getSeconds()).padStart(2, "0");
	const ngay = String(date.getDate()).padStart(2, "0");
	const thang = String(date.getMonth() + 1).padStart(2, "0"); // Lưu ý: Tháng bắt đầu từ 0
	const nam = date.getFullYear();

	const thoiGianMoi = `${gio}:${phut}:${giay} ${ngay}/${thang}/${nam}`;

	return thoiGianMoi;
}

function chuyenDoiDinhDangThoiGian(thoiGian) {
	// Tách thời gian và ngày tháng từ chuỗi đầu vào
	const [gioPhutGiay, ngayThangNam] = thoiGian.split(' ');

	const [gio, phut, giay] = gioPhutGiay.slice(0, -2).split(':').map(Number);
	const ampm = gioPhutGiay.slice(-2);

	let gio24 = gio;
	if (ampm === 'PM' && gio < 12) {
		gio24 += 12;
	} else if (ampm === 'AM' && gio === 12) {
		gio24 = 0;
	}

	// Kết hợp lại thành chuỗi mới với định dạng mong muốn
	const thoiGianMoi = `${String(gio24).padStart(2, '0')}:${phut}:${giay} ${ngayThangNam}`;

	return thoiGianMoi;
}

function tinhKhoangCach(thoiGian1, thoiGian2) {
	const [gio1, phut1, giay1, ngay1, thang1, nam1] = thoiGian1.split(/[\s:\/]+/);
	const date1 = new Date(nam1, thang1 - 1, ngay1, gio1, phut1, giay1);
	const [gio2, phut2, giay2, ngay2, thang2, nam2] = thoiGian2.split(/[\s:\/]+/);
	const date2 = new Date(nam2, thang2 - 1, ngay2, gio2, phut2, giay2);
	const khoangCach = Math.abs(date2 - date1);

	const phut = Math.floor(khoangCach / (1000 * 60));
	const gio = Math.floor(phut / 60);
	const ngay = Math.floor(gio / 24);

	if (ngay >= 1) {
		return `${ngay} ngày trước`;
	} else if (gio >= 1) {
		return `${gio} giờ trước`;
	} else {
		if (phut == 0) return '1 giây trước';
		return `${phut} phút trước`;
	}
}

// ajax bình luận và yêu thích
function addCommentAjax() {
	var contentCm = $("#contentCm").val();
	$.ajax({
		type: "POST",
		url: "/api/comment/save",
		data: {
			slug: slugFilmS,
			contentCm: contentCm
		},
		dataType: 'html',
		timeout: 100000,
		success: function(data) {
			var commentsArray = JSON.parse(data);
			var result = '<div class="anime__details__review">'
				+ '<div class="anime__review__item">'
				+ '<div class="anime__review__item__pic">'
				+ '<img src="' + commentsArray.image + '" alt="">'
				+ '<img src="img/avatar/khung.gif" alt="" class="frame_avatar">'
				+ '</div>'
				+ '<div class="anime__review__item__text">'
				+ '<div class="comment_header">'
				+ '<div class="comment_author">'
				+ '<span class="pbbm" data-tooltip="Đạo Tông"><img src="https://hoathinh3d.tube/wp-content/uploads/2024/02/dao-tong.gif" class="pbbm" alt="Đạo Tông" width="30px"></span>'
				+ commentsArray.name
				+ '<span class="clan" data-tooltip="Đệ Tử">🏯Tiêu Dao⚔︎</span>'
				+ '</div>'
				+ '<div class="mycred-wrap">'
				+ '<img alt="" src="img/avatar/vo-thien-tan.webp">'
				+ '</div>'
				+ '<span style="color: #fff">' + tinhKhoangCach(chuyenDoiDinhDangThoiGian(commentsArray.createAt), chuyenDoiThoiGian(new Date())) + '</span>'
				+ '</div>'
				+ '<input class="contentComment" value=' + commentsArray.content + ' readonly>'
				+ '<span class="settingComment"><i class="fa fa-cog"></i></span>'
				+ '</div>'
				+ '</div>'
				+ '</div>'
			$("#comments").get(0).insertAdjacentHTML('afterbegin', result);
			document.querySelector("#contentCm").value = "";
			document.querySelector(".noComment").style.display = "none"
		},
		error: function(e) {
			console.log("ERROR: ", e);
		}
	});
}

function addFavMove() {
	let name_movie = $(".name_movie").val();
	let img_movie = $(".img_movie").val();
	let episode = $(".episode").val();
	let view_movie = $(".view_movie").val();

	$.ajax({
		type: "POST",
		url: "/detail-film",
		data: {
			slug: slugFilmS,
			name_movie: name_movie,
			img_movie: img_movie,
			episode: episode,
			view_movie: view_movie,
		},
		dataType: 'html',
		timeout: 100000,
		success: function(data) {
			const showFl = document.querySelector(".showFl");
			if (data == "Thêm thành công") showFl.value = "Hủy Theo Dõi 🤍"
			else showFl.value = "Theo Dõi 🤍"
			alert(data);
		},
		error: function(e) {
			console.log("ERROR: ", e);
		}
	});
}

function editComment(contentCm, slugFilmS, idCm){
	$.ajax({
		type: "POST",
		url: "/api/comment/save",
		data: {
			id: idCm,
			slug: slugFilmS,
			contentCm: contentCm
		},
		dataType: 'html',
		timeout: 100000,
		success: function(data) {
			console.log(data);
		},
		error: function(e) {
			console.log("ERROR: ", e);
		}
	})
}