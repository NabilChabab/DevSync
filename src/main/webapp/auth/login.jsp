<!---<html>

<head>
    <title>Login</title>
</head>

<body>
    <h1>Login</h1>
    <form action="login" method="post">
        <label for="email">Username:</label>
        <input type="email" id="email" name="email" required>
        <br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
        <br>
        <input type="submit" value="Login">
    </form>
</body>

</html>
-->

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="dns-prefetch" href="//fonts.bunny.net">
    <link href="https://fonts.bunny.net/css?family=Nunito" rel="stylesheet">
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">

    <link href="#" rel="icon">
    <link href="#" rel="apple-touch-icon">

    <!-- Google Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Protest+Riot&display=swap" rel="stylesheet">
    <link
        href="https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,600;1,700&family=Inter:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&family=Cardo:ital,wght@0,400;0,700;1,400&display=swap"
        rel="stylesheet">

    <!-- Vendor CSS Files -->
    <link href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/vendor/aos/aos.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/user/css/styles.css">
    <script src="https://js.stripe.com/v3/"></script>
</head>
<style>
    .bg-glass {
        background-color: hsla(0, 94%, 26%, 0.267) !important;
        backdrop-filter: saturate(200%) blur(25px);
    }

    .bg-glass2 {
        background-color: hsla(0, 0%, 0%, 0.555) !important;
        backdrop-filter: saturate(200%) blur(25px);
    }

    .custom-popup-class {
        background-color: rgb(24, 24, 24);
    }



    .error input {
        border: 3px solid red;
    }

    .success input {
        border: 3px solid green;
    }

    form span.error-msg {
        color: red;
        width: 100%;
        display: flex;
        margin-left: 30%;
        margin-bottom: 20px;
    }

    form {
        padding: 20px;
        font-family: 'Poppins', sans-serif;
    }

    form a {
        margin-left: 25%;
        text-decoration: none;
    }

    body {
        height: 100vh;
        width: 100%;
        background-position: center;
        background-size: cover;
        object-fit: cover;


    }

    .cards {
        width: 100%;
        border: none;
        background-color: transparent;
        border: none;
        display: flex;
        justify-content: center;
        align-items: center;
        flex-direction: column
    }

    .cards img {
        width: 200px;
        height: 200px;
        border-radius: 50%;
        object-fit: cover;
    }

    .cards label {
        margin-top: 30px;
        text-align: center;
        height: 40px;
        cursor: pointer;
        font-weight: bold;
        font-size: 20px;
        margin-bottom: 10px;

    }

    .cards input {
        display: none;
    }

    .table-responsive {
        width: 100%;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
    }

    .form-outline input {
        background-color: rgb(37, 37, 37);
        border: none;
        width: 100%;
        padding: 15px;
        margin-top: 30px;
        color: rgb(187, 187, 187);

    }

    .form-outline select {
        background-color: rgb(37, 37, 37);
        border: none;
        width: 100%;
        padding: 15px;
        margin-top: 30px;
        color: rgb(187, 187, 187);

    }

    .pagination {
        display: flex;
        justify-content: center;
        list-style: none;
        padding: 0;
    }

    .pagination li {
        margin: 0 5px;
    }

    .pagination li a {
        display: inline-block;
        background-color: transparent;
        color: brown;
        border: none;
        text-decoration: none;
    }

    .pagination li .active a {
        background-color: brown;
        color: #6d0000;
    }
</style>

<body>


<main id="main">

    <div class="container" style="margin-top: 10%">
        <div class="row gx-lg-5 align-items-center">
            <div class="col-lg-6 mb-5 mb-lg-0" style="z-index: 10">
                <h1 class="my-5 display-5 fw-bold ls-tight" style="color: hsl(218, 81%, 95%)">
                    Welcome To <span style="font-family: 'Protest Riot', sans-serif;color:brown">D E V S Y N C</span> <br />
                    <span style="color: white">All on One Platform</span>
                </h1>
                <p class="mb-4 opacity-70" style="color: white">
                    Lorem ipsum dolor, sit amet consectetur adipisicing elit.
                    Temporibus, expedita iusto veniam atque, magni tempora mollitia
                    dolorum consequatur nulla, neque debitis eos reprehenderit quasi
                    ab ipsum nisi dolorem modi. Quos?
                </p>
            </div>

            <div class="col-lg-6 mb-5 mb-lg-0 position-relative">
                <div id="radius-shape-1" class="position-absolute rounded-circle shadow-5-strong"></div>
                <div id="radius-shape-2" class="position-absolute shadow-5-strong"></div>

                <div class="card1 bg-glass">
                    <div class="card-body px-4 py-5 px-md-5">

                        <form action="login" method="POST" id="form">
                            <div class="form-outline mb-4">
                                <input type="email" id="email" class="form-control p-2 bg-light text-dark" placeholder="Email" name="email" />
                                <c:if test="${not empty emailError}">
                                    <p class="form-error text-danger mt-2">${emailError}</p>
                                </c:if>
                            </div>

                            <div class="form-outline mb-4">
                                <input type="password" id="password" class="form-control p-2 bg-light text-dark" placeholder="Password" name="password" />
                                <c:if test="${not empty passwordError}">
                                    <p class="form-error text-danger mt-2">${passwordError}</p>
                                </c:if>
                            </div>
                            <a class="btn text-light text-center mb-3" href="" style="text-align:end;width:100%">Forgot Your Password?
                            </a>

                            <c:if test="${not empty loginError}">
                                <p class="form-error text-danger">${loginError}</p>
                            </c:if>

                            <button type="submit" class="btn btn-dark btn-block mb-4 col-12 p-2">Login</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>



</main>
<footer id="footer" class="footer">
    <div class="container">
        <div class="copyright">
            &copy; Copyright <strong><span>PhotoFolio</span></strong>. All Rights Reserved
        </div>
        <div class="credits">
            <!-- All the links in the footer should remain intact. -->
            <!-- You can delete the links only if you purchased the pro version. -->
            <!-- Licensing information: https://bootstrapmade.com/license/ -->
            <!-- Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/photofolio-bootstrap-photography-website-template/ -->
            Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
        </div>
    </div>
</footer>

<a href="#" class="scroll-top d-flex align-items-center justify-content-center"><i
        class="bi bi-arrow-up-short"></i></a>




<script src="${pageContext.request.contextPath}/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/vendor/swiper/swiper-bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/vendor/glightbox/js/glightbox.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/vendor/aos/aos.js"></script>
<script src="${pageContext.request.contextPath}/assets/vendor/php-email-form/validate.js"></script>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>


<!-- Template Main JS File -->
<script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
<script src="${pageContext.request.contextPath}/js/form.js"></script>
<script src="${pageContext.request.contextPath}/js/login.js"></script>
<script src="${pageContext.request.contextPath}/js/search.js"></script>



</body>

</html>
