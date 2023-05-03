<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/index.css">
<style type="text/css">
.video {
	width: 100%;
	height: 326px;
}

#fluid_video_wrapper_video {
	width: 100% !important;
	height: 100% !important;
}
</style>
</head>
<body>
	<div class="container" style="height: 100vh;">
		<jsp:include page="sharePage/left.jsp"></jsp:include>
		<div class="wrapper">
			<jsp:include page="sharePage/header.jsp"></jsp:include>
			<div class="main-container show" style="margin-top: 120px;">

				<div class="stream-area">
					<div class="video-stream">
						<div class="video">
							<video id="video" autoplay="autoplay">
								<source src="${video.links}" type="video/mp4" />
						</div>

						<div class="video-detail">
							<div class="video-content">
								<div class="video-p-title anim" style="-delay: .2s">${video.nameVideo}</div>
								<div class="video-p-wrapper anim" style="-delay: .1s">
									<div class="author-img__wrapper video-author video-p">
										<svg viewBox="0 0 24 24" fill="none" stroke="currentColor"
											stroke-width="3" stroke-linecap="round"
											stroke-linejoin="round" class="feather feather-check">
                                            <path d="M20 6L9 17l-5-5" />
                                        </svg>
										<img class="author-img" src="${video.account.avartar}" />
									</div>
									<div class="video-p-detail">
										<div class="video-p-name">${video.account.fullname}</div>
									</div>
									<div class="button-wrapper">
										<a href="like?vid=${video.videoID}"
											style="color: ${style == null ? " black":"red"}; background:
											white;" class="like red"> <svg viewBox="0 0 24 24"
												fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                <path
													fill-rule="evenodd" clip-rule="evenodd"
													d="M15.85 2.5c.63 0 1.26.09 1.86.29 3.69 1.2 5.02 5.25 3.91 8.79a12.728 12.728 0 01-3.01 4.81 38.456 38.456 0 01-6.33 4.96l-.25.15-.26-.16a38.093 38.093 0 01-6.37-4.96 12.933 12.933 0 01-3.01-4.8c-1.13-3.54.2-7.59 3.93-8.81.29-.1.59-.17.89-.21h.12c.28-.04.56-.06.84-.06h.11c.63.02 1.24.13 1.83.33h.06c.04.02.07.04.09.06.22.07.43.15.63.26l.38.17c.092.05.195.125.284.19.056.04.107.077.146.1l.05.03c.085.05.175.102.25.16a6.263 6.263 0 013.85-1.3zm2.66 7.2c.41-.01.76-.34.79-.76v-.12a3.3 3.3 0 00-2.11-3.16.8.8 0 00-1.01.5c-.14.42.08.88.5 1.03.64.24 1.07.87 1.07 1.57v.03a.86.86 0 00.19.62c.14.17.35.27.57.29z" />
                                            </svg> Liked
										</a>
									</div>
								</div>
								<div class="video-p-subtitle anim"
									style="-delay: .3s; margin-top: 15px">${video.description}</div>
							</div>
						</div>
					</div>
					<div class="chat-stream">
						<div class="chat-vid__container" style="margin: 0px">
							<div class="chat-vid__title anim" style="-delay: .3s">Video
								tương tự</div>
							<c:forEach items="${videoCategory}" var="o">
								<a href="watch?video=${o.videoID}&cid=${o.category.categoryID}"><div
										class="chat-vid anim" style="-delay: .5s">
										<div class="chat-vid__wrapper">
											<img style="width: 200px; height: 110px;"
												class="chat-vid__img" src="${o.images }" />
											<div class="chat-vid__content">
												<div class="chat-vid__name">${o.nameVideo}</div>
												<div class="chat-vid__by">${o.account.fullname}</div>
												<div class="chat-vid__info">
													${o.views} lượt xem <span class="seperate"></span>${o.dateCreated}
												</div>
											</div>
										</div>
									</div></a>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script src="https://cdn.fluidplayer.com/v3/current/fluidplayer.min.js"></script>
	<script type="text/javascript" src="js/watch.js"></script>
</body>
</html>