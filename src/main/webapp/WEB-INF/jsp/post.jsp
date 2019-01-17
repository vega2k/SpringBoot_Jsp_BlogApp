<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
    <title>Spring Blog</title>
</head>

<body>

	<%@ include file="/WEB-INF/jspf/nav.jspf" %>

    <!-- Page Header -->
    <!-- Set your background image for this header on the line below. -->
    <header class="intro-header" style="background-image: url('/webjars/shue-startbootstrap-clean-blog/3.3.7/img/post-bg.jpg')">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                    <div class="post-heading">
                        <h1>${post.subject}</h1>
                        <span class="meta">Posted by <a href="#">Start Bootstrap</a> ${post.regDate}</span>
                    </div>
                </div>
            </div>
        </div>
    </header>

    <!-- Post Content -->
    <article>
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                    ${post.content}
                </div>
            </div>
            <div class="pull-right">
            	<a href="/post/${post.id}/edit">
					<button type="button" class="btn btn-warning">Edit</button>
				</a>
				<a href="/post/${post.id}/delete" onclick="if(!confirm('삭제하시겠습니까?')){return false;}">
					<button type="button" class="btn btn-danger">Delete</button>
				</a>
			</div>
        </div>
    </article>

    <hr>

	<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>

</html>