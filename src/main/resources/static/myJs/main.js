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
	if (idUserCmt != null) idUser = parseInt(idUserCmt.innerHTML);
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
                                                        src="https://hoathinh3d.io/wp-content/uploads/2024/02/dao-tong.gif"
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
                                        <input class="contentComment" value='${item.content}' readonly>
                                        <div class="updateCm">
                                        <button type="submit" class="btnUpdate">Lưu</button>
                                        <button type="submit" class="btnClose">Hủy</button></div>
                                        ${item.idUser == idUser ? `<span class="settingComment"><i class="fa fa-cog"></i><ul class="list-group listSettingCm">  
                                        	<li class="list-group-item clickEditComment" id="${item.id}">Chỉnh sửa</li>
                                        	<li class="list-group-item clickDeleteComment" id="${item.id}">Xóa</li></ul></span>` : ''}
                                        ${item.edit_comment == "1" ? '<div class="isCheckEditCM" style="color: #fff; font-size: 10px"><i class="fa fa-pencil-square-o"></i> Đã chỉnh sửa</div>' : ''}
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
					updateCm.style.display = "block";
					updateCm.querySelector(".btnUpdate").onclick = () => {
						editComment(contentComment.value, slugFilmS, clickEditComment[i].getAttribute("id"));
						updateCm.style.display = "none";
						contentComment.readOnly = true;
						if (content.querySelector(".isCheckEditCM") == null)
							content.insertAdjacentHTML('beforeend', '<div class="isCheckEditCM" style="color: #fff; font-size: 10px"><i class="fa fa-pencil-square-o"></i> Đã chỉnh sửa</div>');
					}
					updateCm.querySelector(".btnClose").onclick = () => {
						updateCm.style.display = "none";
						contentComment.readOnly = true;
					}
				}
			}
			
			const clickDeleteComment = document.querySelectorAll(".clickDeleteComment");
			for (let i = 0; i < clickDeleteComment.length; i++) {
				clickDeleteComment[i].onclick = (e) => {
					deleteComment(e.target.getAttribute("id"));
				}
			}
		})
		.catch(error => {
			console.error('Có lỗi xảy ra' + error);
		});
}

const formNoClick = document.querySelector(".formNoClick");
if (formNoClick != null) formNoClick.addEventListener("submit", function(event) { event.preventDefault(); });

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

	const giay = Math.floor(khoangCach / 1000);
	const phut = Math.floor(giay / 60);
	const gio = Math.floor(phut / 60);
	const ngay = Math.floor(gio / 24);

	if (ngay >= 1) {
		return `${ngay} ngày trước`;
	} else if (gio >= 1) {
		return `${gio} giờ trước`;
	} else {
		if (phut == 0) {
			if (giay == 0) return '1 giây trước';
			return `${giay} giây trước`;
		}
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
			const idUserCmt = document.querySelector(".idUserCmt");
			let idUser = -1;
			if (idUserCmt != null) idUser = parseInt(idUserCmt.innerHTML);
			
	        // Xóa bình luận cuối cùng để giữ cho chỉ hiển thị 6 bình luận
			const currentCommentsCount = document.querySelectorAll(".anime__details__review").length;
		    if (currentCommentsCount >= 6) {
		        document.querySelector(".anime__details__review:last-child").remove();
		    }
		    
			var result = '<div class="anime__details__review">'
				+ '<div class="anime__review__item">'
				+ '<div class="anime__review__item__pic">'
				+ '<img src="' + commentsArray.image + '" alt="">'
				+ '<img src="img/avatar/khung.gif" alt="" class="frame_avatar">'
				+ '</div>'
				+ '<div class="anime__review__item__text">'
				+ '<div class="comment_header">'
				+ '<div class="comment_author">'
				+ '<span class="pbbm" data-tooltip="Đạo Tông"><img src="https://hoathinh3d.io/wp-content/uploads/2024/02/dao-tong.gif" class="pbbm" alt="Đạo Tông" width="30px"></span>'
				+ commentsArray.name
				+ '<span class="clan" data-tooltip="Đệ Tử">🏯Tiêu Dao⚔︎</span>'
				+ '</div>'
				+ '<div class="mycred-wrap">'
				+ '<img alt="" src="img/avatar/vo-thien-tan.webp">'
				+ '</div>'
				+ '<span style="color: #fff">' + tinhKhoangCach(chuyenDoiDinhDangThoiGian(commentsArray.createAt), chuyenDoiThoiGian(new Date())) + '</span>'
				+ '</div>'
				+ '<input class="contentComment" value="' + commentsArray.content + '" readonly>'
				+ '<div class="updateCm">'
				+ '<button type="submit" class="btnUpdate">Lưu</button>'
				+ '<button type="submit" class="btnClose">Hủy</button></div>'
				+ `${commentsArray.idUser == idUser ? `<span class="settingComment"><i class="fa fa-cog"></i><ul class="list-group listSettingCm">
                                        	<li class="list-group-item clickEditComment" id="${commentsArray.id}">Chỉnh sửa</li>
                                        	<li class="list-group-item clickDeleteComment" id="${commentsArray.id}">Xóa</li></ul></span>` : ''}`
				+ `${commentsArray.edit_comment == "1" ? '<div class="isCheckEditCM" style="color: #fff; font-size: 10px"><i class="fa fa-pencil-square-o"></i> Đã chỉnh sửa</div>' : ''}`
				+ '</div>'
				+ '</div>'
				+ '</div>'
			$("#comments").get(0).insertAdjacentHTML('afterbegin', result);			
			const clickEditComment = document.querySelector(".clickEditComment");
			clickEditComment.onclick = () => {
				const content = clickEditComment.parentElement.parentElement.parentElement;
				const contentComment = content.querySelector(".contentComment");
				contentComment.removeAttribute("readonly");
				contentComment.focus();
				const updateCm = content.querySelector(".updateCm");
				updateCm.style.display = "block";
				updateCm.querySelector(".btnUpdate").onclick = () => {
					editComment(contentComment.value, slugFilmS, clickEditComment.getAttribute("id"));
					updateCm.style.display = "none";
					contentComment.readOnly = true;
					if (content.querySelector(".isCheckEditCM") == null)
						content.insertAdjacentHTML('beforeend', '<div class="isCheckEditCM" style="color: #fff; font-size: 10px"><i class="fa fa-pencil-square-o"></i> Đã chỉnh sửa</div>');
				}
				updateCm.querySelector(".btnClose").onclick = () => {
					updateCm.style.display = "none";
					contentComment.readOnly = true;
				}
			}
			
			document.querySelector(".clickDeleteComment").onclick = (e) => {
					deleteComment(e.target.getAttribute("id"));
			}

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

function editComment(contentCm, slugFilmS, idCm) {
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
			console.log("Tiến đẹp zai");
		},
		error: function(e) {
			console.log("ERROR: ", e);
		}
	})
}

function deleteComment(idCm) {
	$.ajax({
		type: "POST",
		url: "/api/comment/delete",
		data: {
			id: idCm,
		},
		dataType: 'html',
		timeout: 100000,
		success: function(data) {
			const clickDeleteComment = document.querySelectorAll(".clickDeleteComment");
			for (let i = 0; i < clickDeleteComment.length; i++) {
				if(clickDeleteComment[i].getAttribute("id") == data){
					clickDeleteComment[i].parentElement.parentElement.parentElement.parentElement.parentElement.style.display = "none";
				}
			}
		},
		error: function(e) {
			console.log("ERROR: ", e);
		}
	})
}