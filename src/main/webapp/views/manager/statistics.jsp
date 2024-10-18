
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" rel="stylesheet" />
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
    <!-- Nucleo Icons -->
    <link href="${pageContext.request.contextPath}/public/css/nucleo-icons.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/public/css/nucleo-svg.css" rel="stylesheet" />
    <!-- Font Awesome Icons -->
    <script src="https://kit.fontawesome.com/42d5adcbca.js" crossorigin="anonymous"></script>
    <!-- CSS Files -->
    <link id="pagestyle" href="${pageContext.request.contextPath}/public/css/argon-dashboard.css?v=2.0.4" rel="stylesheet" />
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">


</head>
<style>

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
        width: 70px;
        height: 70px;
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

    .success{
        background-color: #D9FDEF !important;
        color: #216E64 !important;
    }
    .warning{
        background-color: #F1F2F4 !important;
        color: #172B4D !important;
    }
    .primary {
        background-color: #D6E4FF !important;
        color: #2B44BD !important;
    }
    .danger {
        background-color: #FEE2E2 !important;
        color: #C53030 !important;
    }
</style>
<body class="g-sidenav-show" style="background-color:white;">
<div class="min-height-300 position-absolute w-100" style="background-color:#5E72E4;"></div>
<aside class="sidenav navbar navbar-vertical navbar-expand-xs border-0 border-radius-xl my-3 fixed-start ms-4 "
       id="sidenav-main" style="background-color:white;z-index: 999">
    <div class="sidenav-header">
        <i class="fas fa-times p-3 cursor-pointer text-secondary opacity-5 position-absolute end-0 top-0 d-none d-xl-none"
           aria-hidden="true" id="iconSidenav"></i>
        <a class="navbar-brand m-0" href=" https://demos.creative-tim.com/argon-dashboard/pages/dashboard.html "
           target="_blank">
            <span class="ms-1 font-weight-bold">D E V S Y N C</span>

        </a>
    </div>
    <hr class="horizontal dark mt-0">
    <div class="collapse navbar-collapse  w-auto " id="sidenav-collapse-main">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="">
                    <div
                            class="icon icon-shape icon-sm border-radius-md text-center me-2 mb-2 d-flex align-items-center justify-content-center">
                        <i class="bx bx-home text-dark text-sm opacity-10"></i>
                    </div>
                    <span class="nav-link-text ms-1">Dashboard</span>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="tasks">
                    <div
                            class="icon icon-shape icon-sm border-radius-md text-center me-2 mb-2 d-flex align-items-center justify-content-center">
                        <i class="bx bx-task text-danger text-sm opacity-10"></i>
                    </div>
                    <span class="nav-link-text ms-1">Tasks</span>
                </a>
            </li>

            <li class="nav-item">
                <a class="nav-link active" href="" style="background-color: #5E72E4;">
                    <div
                            class="icon icon-shape icon-sm border-radius-md text-center me-2 mb-2 d-flex align-items-center justify-content-center">
                        <i class="bx bx-chart text-success text-sm opacity-10"></i>
                    </div>
                    <span class="nav-link-text text-white ms-1">Statistics</span>
                </a>
            </li>


        </ul>
    </div>

</aside>
<main class="main-content position-relative border-radius-lg ">
    <!-- Navbar -->
    <nav class="navbar navbar-main navbar-expand-lg px-0 mx-4 shadow-none border-radius-xl " id="navbarBlur"
         data-scroll="false">
        <div class="container-fluid py-1 px-3">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb bg-transparent mb-0 pb-0 pt-1 px-0 me-sm-6 me-5">
                    <li class="breadcrumb-item text-sm"><a class="opacity-5 text-white"
                                                           href="javascript:;">Pages</a></li>
                    <li class="breadcrumb-item text-sm text-white active" aria-current="page">Dashboard</li>
                </ol>
                <h6 class="font-weight-bolder text-white mb-0">Dashboard</h6>
            </nav>
            <div class="collapse navbar-collapse mt-sm-0 mt-2 me-md-0 me-sm-4" id="navbar">
                <div class="ms-md-auto pe-md-3 d-flex align-items-center">
                    <div class="input-group">
                            <span class="input-group-text text-body"><i class="fas fa-search"
                                                                        aria-hidden="true"></i></span>
                        <input type="text" class="form-control" placeholder="Type here...">
                    </div>
                </div>
                <ul class="navbar-nav  justify-content-end">
                    <li class="nav-item d-flex align-items-center">
                        <a class="nav-link text-white font-weight-bold px-0" href=""
                        >

                            <img src="${pageContext.request.contextPath}/${sessionScope.user.profile}" alt="" srcset=""
                                 style="width: 40px;height:40px;border-radius:50%;margin-right:10px;">

                            <form action="../auth/login" method="post" style="display:inline;" onsubmit="return confirm('Are you sure you want to logout?');">
                                <input type="hidden" name="_logout" value="logout">
                                <button style="border: none;border: none;background-color: transparent"><span class="d-sm-inline d-none text-white">Logout</span></button>
                            </form>
                        </a>
                    </li>
                    <li class="nav-item d-xl-none ps-3 d-flex align-items-center">
                        <a href="javascript:;" class="nav-link text-white p-0" id="iconNavbarSidenav">
                            <div class="sidenav-toggler-inner">
                                <i class="sidenav-toggler-line bg-white"></i>
                                <i class="sidenav-toggler-line bg-white"></i>
                                <i class="sidenav-toggler-line bg-white"></i>
                            </div>
                        </a>
                    </li>
                    <li class="nav-item px-3 d-flex align-items-center">
                        <a href="javascript:;" class="nav-link text-white p-0">
                            <i class="fa fa-cog fixed-plugin-button-nav cursor-pointer"></i>
                        </a>
                    </li>
                    <li class="nav-item dropdown pe-2 d-flex align-items-center">
                        <a href="javascript:;" class="nav-link text-white p-0" id="dropdownMenuButton"
                           data-bs-toggle="dropdown" aria-expanded="false">
                            <i class="fa fa-bell cursor-pointer"></i>
                        </a>

                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <!-- End Navbar -->
    <div class="container-fluid py-4">
        <div class="col-4">
            <div class="card mb-4" style="background-color:white;height: 90%" >
                <div class="card-header pb-0 d-flex justify-content-between align-items-center"
                     style="background-color:white;">
                    <h6>Filter Tasks By Period</h6>
                    <button href="" class="btn btn-primary"  onclick="filterTasksByDate()">Filter</button>

                </div>
                <div class="card-body px-0 pt-0 pb-2">
                    <div class="table-responsive p-0">
                        <table class="table align-items-center mb-0">
                            <thead>
                            <tr>
                                <th class="text-uppercase text-center text-secondary text-xxs font-weight-bolder opacity-7">
                                    Start Date</th>
                                <th
                                        class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">
                                    End Date</th>

                            </tr>
                            </thead>
                            <tbody>

                            <tr>
                                <td class="align-middle text-start">
                                    <input type="date" id="filterStartDate" class="form-control bg-light">
                                </td>
                                <td class="align-middle text-start">
                                    <input type="date" id="filterEndDate" class="form-control bg-light">
                                </td>
                            </tr>
                            </tbody>

                        </table>
                    </div>
                </div>
            </div>
        </div>
        <div class="row mt-4">
            <div class="row">
                <div class="col-6">
                    <div class="card mb-4" style="background-color:white;">
                        <div class="card-header pb-0 d-flex justify-content-between align-items-center"
                             style="background-color:white;">
                            <h6>Statistics</h6>
                            <a href="" class="btn btn-primary" data-bs-toggle="modal"
                               data-bs-target="#addUser">Create One</a>

                        </div>
                        <div class="card-body px-0 pt-0 pb-2">
                            <div class="table-responsive p-0">
                                <table class="table align-items-center mb-0">
                                    <thead>
                                    <tr>
                                        <th class="text-uppercase text-center text-secondary text-xxs font-weight-bolder opacity-7">
                                            Status</th>
                                        <th
                                                class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">
                                            Progress</th>

                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td class="align-middle text-start">
                                            <span class="text-xs font-weight-bold">ToDo</span>
                                        </td>
                                        <td class="align-middle text-start">
                                            <div class="progress" role="progressbar" style="width: 100%; height: 20px;" aria-label="Example with label" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100">
                                                <div class="progress-bar bg-warning" style="width: ${tasksTodo}0%; height: 20px;">${tasksTodo}%</div>
                                            </div>
                                        </td>

                                    </tr>
                                    <tr>
                                        <td class="align-middle text-start">
                                            <span class="text-xs font-weight-bold">In Progress</span>
                                        </td>
                                        <td class="align-middle text-start">
                                            <div class="progress" role="progressbar" style="width: 100%; height: 20px;" aria-label="Example with label" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100">
                                                <div class="progress-bar" style="width: ${tasksInProgress}0%; height: 20px;">${tasksInProgress}%</div>
                                            </div>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td class="align-middle text-start">
                                            <span class="text-xs font-weight-bold">Done</span>
                                        </td>
                                        <td class="align-middle text-start">
                                            <div class="progress" role="progressbar" style="width: 100%; height: 20px;" aria-label="Example with label" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100">
                                                <div class="progress-bar bg-success" style="width: ${tasksDone}0%; height: 20px;">${tasksDone}%</div>
                                            </div>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td class="align-middle text-start">
                                            <span class="text-xs font-weight-bold">Canceled</span>
                                        </td>
                                        <td class="align-middle text-start">
                                            <div class="progress" role="progressbar" style="width: 100%; height: 20px;" aria-label="Example with label" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100">
                                                <div class="progress-bar bg-danger" style="width: ${tasksCanceled}0%; height: 20px;">${tasksCanceled}%</div>
                                            </div>
                                        </td>
                                    </tr>
                                    </tbody>

                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-6">
                    <div class="card mb-4" style="background-color:white;">
                        <div class="card-header pb-0 d-flex justify-content-between align-items-center"
                             style="background-color:white;">
                            <h6>Statistics</h6>
                            <a href="" class="btn btn-primary" data-bs-toggle="modal"
                               data-bs-target="#addUser">Create One</a>

                        </div>
                        <div class="card-body px-0 pt-0 pb-2">
                            <div class="table-responsive p-0">
                                <table class="table align-items-center mb-0">
                                    <thead>
                                    <tr>
                                        <th class="text-uppercase text-center text-secondary text-xxs font-weight-bolder opacity-7">
                                            Status</th>
                                        <th
                                                class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">
                                            Progress</th>

                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td class="align-middle text-start">
                                            <span class="text-xs font-weight-bold">Today Tasks</span>
                                        </td>
                                        <td class="align-middle text-start">
                                            <div class="progress" role="progressbar" style="width: 100%; height: 20px;" aria-label="Example with label" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100">
                                                <div class="progress-bar bg-warning" style="width: ${tasksToday}0%; height: 20px;">${tasksToday}%</div>
                                            </div>
                                        </td>

                                    </tr>
                                    <tr>
                                        <td class="align-middle text-start">
                                            <span class="text-xs font-weight-bold">Assigned Tasks</span>
                                        </td>
                                        <td class="align-middle text-start">
                                            <div class="progress" role="progressbar" style="width: 100%; height: 20px;" aria-label="Example with label" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100">
                                                <div class="progress-bar" style="width: ${assignedTasksCount}0%; height: 20px;">${assignedTasksCount}%</div>
                                            </div>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td class="align-middle text-start">
                                            <span class="text-xs font-weight-bold">Token Uses</span>
                                        </td>
                                        <td class="align-middle text-start">
                                            <div class="progress" role="progressbar" style="width: 100%; height: 20px;" aria-label="Example with label" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100">
                                                <div class="progress-bar bg-success" style="width: ${tokenUses}0%; height: 20px;">${tokenUses}%</div>
                                            </div>
                                        </td>
                                    </tr>
                                    </tbody>

                                </table>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-12">
                    <div class="card mb-4" style="background-color:white;">
                        <div class="card-header pb-0 d-flex justify-content-between align-items-center"
                             style="background-color:white;">
                            <h6>Tasks</h6>

                        </div>
                        <div class="card-body px-0 pt-0 pb-2" style="height: 100vh;">
                            <div class="table-responsive p-0">
                                <table class="table align-items-center mb-0" style="height: auto">
                                    <thead>
                                    <tr>
                                        <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">
                                            ID</th>
                                        <th
                                                class="text-start text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">
                                            Title</th>
                                        <th
                                                class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">
                                            Status</th>

                                        <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">
                                            Start Date</th>
                                        <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">
                                            End Date</th>
                                        <th
                                                class="text-start text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">
                                            Assigned</th>
                                        <th
                                                class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">
                                            Action</th>



                                    </tr>
                                    </thead>
                                    <tbody class="tbody">
                                    <c:forEach var="task" items="${tasks}">
                                        <tr data-start-date="${task.startDate}" data-end-date="${task.endDate}">
                                            <td>${task.id}</td>
                                            <td class="align-middle text-start">${task.title}</td>
                                            <td class="align-middle text-center">
                                                <form action="${pageContext.request.contextPath}/manager/tasks" method="POST">
                                                    <input type="hidden" name="task_id" value="${task.id}" />
                                                    <div class="dropdown">
                                                        <button class="btn  btn-sm dropdown-toggle ${task.status == 'DONE' ? 'success' : task.status == 'IN_PROGRESS' ? 'primary': task.status == 'CANCELLED' ? 'danger' : 'warning'}" type="button" id="statusDropdown${task.id}" data-bs-toggle="${task.status == 'CANCELLED' ? '' : 'dropdown'}" aria-expanded="false" style="border-radius: 5px !important;">
                                                                ${task.status}
                                                        </button>
                                                        <ul class="dropdown-menu" aria-labelledby="statusDropdown${task.id}">
                                                            <li class="text-center">
                                                                <button class="dropdown-item text-dark font-weight-bold" type="submit" name="status" value="TODO">Todo</button>
                                                            </li>
                                                            <li class="text-center">
                                                                <button class="dropdown-item text-primary font-weight-bold" type="submit" name="status" value="IN_PROGRESS">Inprogress</button>
                                                            </li>
                                                            <li class="text-center">
                                                                <button class="dropdown-item text-success font-weight-bold" type="submit" name="status" value="DONE">Done</button>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </form>
                                            </td>
                                            <td class="align-middle text-center">
                                                <span class="text-secondary text-xs font-weight-bold">${task.startDate}</span>
                                            </td>
                                            <td class="align-middle text-center">
                                                <span class="text-secondary text-xs font-weight-bold">${task.endDate}</span>
                                            </td>
                                            <form method="POST" action="${pageContext.request.contextPath}/manager/tasks">
                                                <td class="d-flex align-items-center justify-content-between">
                                                    <div class="d-flex align-items-center" style="width: 10px">
                                                        <c:if test="${not empty task.user.profile}">
                                                            <img src="${pageContext.request.contextPath}/${task.user.profile}"
                                                                 class="avatar avatar-sm me-2 border-radius-2xl profilee"
                                                                 alt="user"
                                                                 style="object-fit: cover; width: 40px; height: 40px;">
                                                        </c:if>
                                                        <c:if test="${empty task.user.profile}">
                                                            <img src="${pageContext.request.contextPath}/public/images/avatar.jfif"
                                                                 class="avatar avatar-sm me-2 border-radius-2xl profilee"
                                                                 alt="user"
                                                                 style="object-fit: cover; width: 40px; height: 40px;">
                                                        </c:if>

                                                        <div class="d-flex flex-column align-items-start ms-2">
                                                        <span class="text-secondary text-xs font-weight-bold" id="usernamee">
                                                                ${not empty task.user.username ? task.user.username : 'unassigned'}
                                                        </span>
                                                            <span class="text-secondary text-xs">------</span>
                                                        </div>
                                                    </div>

                                                    <div class="d-flex align-items-center">
                                                        <div class="dropdown ms-3">
                                                            <button class="btn btn-primary bg-transparent dropdown-toggle p-0 shadow-none text-secondary" type="button" data-bs-toggle="dropdown"></button>
                                                            <ul class="dropdown-menu custom-menu">
                                                                <c:forEach var="user" items="${users}">
                                                                    <li class="text-primary-hover">
                                                                        <a class="dropdown-item custom-item" href="#"
                                                                           data-profilee="${pageContext.request.contextPath}/${user.profile}"
                                                                           data-usernamee="${user.username}"
                                                                           data-userIdd="${user.id}"
                                                                           onclick="updateProfileImageTable(event, this);">
                                                                            <img src="${pageContext.request.contextPath}/${user.profile}" alt="${user.username}" class="profile-img">
                                                                            <span>${user.username}</span>
                                                                        </a>
                                                                    </li>
                                                                </c:forEach>
                                                            </ul>
                                                        </div>
                                                        <input type="hidden" id="user_id1" name="userId">
                                                        <input type="hidden" name="task_id" value="${task.id}">
                                                        <button type="submit" class="badge badge-sm bg-gradient-success text-center ms-5 border-0" style="border: none; display: none;width: 20px;height: 20px">
                                                            <i class="bx bx-check text-white"></i>
                                                        </button>
                                                        <c:if test="${sessionScope.tokenRequest.task.id == task.id}">
                                                            <a class="badge badge-sm bg-gradient-danger text-center cursor-pointer ms-5 border-0 d-flex justify-content-center align-items-center"
                                                               data-message="${sessionScope.tokenRequest.message}"
                                                               data-requestId="${sessionScope.tokenRequest.id}"
                                                               data-status="${sessionScope.tokenRequest.requestType}"
                                                               data-bs-toggle="modal"
                                                               data-bs-target="#updateUser"
                                                               style="border: none; width: 20px;height: 20px">
                                                                <i class="bx bxs-circle text-white"></i>
                                                            </a>
                                                        </c:if>
                                                    </div>


                                                </td>
                                            </form>




                                            <td class="align-middle text-center">
                                                <button class="badge badge-sm bg-gradient-primary text-center" data-bs-toggle="offcanvas" data-bs-target="#offcanvasRight" aria-controls="offcanvasRight"
                                                        data-show-tasktitle="${task.title}"
                                                        data-show-task-username="${task.user.username}"
                                                        data-show-description="${task.description}"
                                                        data-show-start="${task.startDate}"
                                                        data-show-end="${task.endDate}"
                                                        data-show-userprofile="${pageContext.request.contextPath}/${task.user.profile}"
                                                        data-show-manager="${task.manager.username}"
                                                        data-show-file="${task.file}"
                                                        data-show-createdAt="${task.createdAt}"
                                                        style="border: none">
                                                    <i class="bx bxs-show"></i>
                                                </button>
                                                <form action="${pageContext.request.contextPath}/manager/tasks" method="post" style="display:inline;" onsubmit="return confirm('Are you sure you want to delete this user?');">
                                                    <input type="hidden" name="id" value="${task.id}">
                                                    <input type="hidden" name="_method" value="delete">
                                                    <button class="badge badge-sm bg-gradient-danger text-center" style="border: none"><i class="bx bxs-trash"></i></button>
                                                </form>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>

                                </table>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <div class="modal fade" id="addUser" tabindex="-1" aria-labelledby="editProfileModalLabel" aria-hidden="true" style="z-index: 100001">
            <div class="modal-dialog" >
                <div class="modal-content" >
                    <div class="modal-header" >
                        <h5 class="modal-title text-dark" id="editProfileModalLabel">Add User</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="${pageContext.request.contextPath}/manager/dashboard" method="post" enctype="multipart/form-data">
                            <div class="mb-3">
                                <div class="cards">
                                    <img src="${pageContext.request.contextPath}/public/images/avatar.jfif" id="image" style="width: 100px ; height: 100px ; object-fit: cover">
                                    <label for="profile" class="text-sm">Choose Image</label>
                                    <input type="file" id="profile" name="profile"  class="form-control bg-transparent mt-3" placeholder="Put you Picture here">

                                </div>
                                <input type="text" id="username" name="username" class="form-control bg-transparent" placeholder="UserName">
                                <input type="text" id="email" name="email" class="form-control bg-transparent mt-3" placeholder="exemple@gmail.com">
                                <input type="password"  id="password" name="password"  class="form-control bg-transparent mt-3" placeholder="Password">
                                <select type="text"  id="role" name="role"  class="form-control bg-transparent mt-3" placeholder="Phone Number">
                                    <option value="MANAGER">MANAGER</option>
                                    <option value="USER">USER</option>
                                </select>
                            </div>
                            <button type="submit" class="btn btn-primary" style="float: right">Create</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="updateUser" tabindex="-1" aria-labelledby="editProfileModalLabel" aria-hidden="true" style="z-index: 100001">
            <div class="modal-dialog" >
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title text-dark" >Update User</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="../manager/dashboard" method="post" enctype="multipart/form-data">
                            <div class="mb-3">
                                <input type="hidden" name="user_id" id="user_id">
                                <input type="text" id="username2" name="username" class="form-control bg-transparent text-dark" placeholder="Name">
                                <input type="text" id="useremail"  name="email" class="form-control bg-transparent mt-3 text-dark" placeholder="exemple@gmail.com">
                            </div>
                            <button type="submit" class="btn btn-primary">Update</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <footer class="footer pt-3  ">
            <div class="container-fluid">
                <div class="row align-items-center justify-content-lg-between">
                    <div class="col-lg-6 mb-lg-0 mb-4">
                        <div class="copyright text-center text-sm text-muted text-lg-start">
                            ©
                            <script>
                                document.write(new Date().getFullYear())
                            </script>,
                            made with <i class="fa fa-heart"></i> by
                            <a href="https://www.creative-tim.com" class="font-weight-bold"
                               target="_blank">E V E N T O</a>
                            for a better platform.
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <ul class="nav nav-footer justify-content-center justify-content-lg-end">
                            <li class="nav-item">
                                <a href="https://www.creative-tim.com" class="nav-link text-muted"
                                   target="_blank">E V E N T O</a>
                            </li>
                            <li class="nav-item">
                                <a href="https://www.creative-tim.com/presentation" class="nav-link text-muted"
                                   target="_blank">About Us</a>
                            </li>
                            <li class="nav-item">
                                <a href="https://www.creative-tim.com/blog" class="nav-link text-muted"
                                   target="_blank">Blog</a>
                            </li>
                            <li class="nav-item">
                                <a href="https://www.creative-tim.com/license" class="nav-link pe-0 text-muted"
                                   target="_blank">License</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </footer>
    </div>
</main>


<script src="${pageContext.request.contextPath}/public/assets/js/core/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/public/assets/js/core/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/public/assets/js/plugins/perfect-scrollbar.min.js"></script>
<script src="${pageContext.request.contextPath}/public/assets/js/plugins/smooth-scrollbar.min.js"></script>
<script src="${pageContext.request.contextPath}/public/assets/js/plugins/chartjs.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>


<script>
    function filterTasksByDate() {
        const filterStartDate = new Date(document.getElementById('filterStartDate').value);
        const filterEndDate = new Date(document.getElementById('filterEndDate').value);

        document.querySelectorAll('.tbody tr').forEach(row => {
            const taskStartDate = new Date(row.getAttribute('data-start-date'));
            const taskEndDate = new Date(row.getAttribute('data-end-date'));

            // Check if task date is within the selected range
            if ((filterStartDate <= taskStartDate && filterEndDate >= taskEndDate) ||
                (filterStartDate <= taskStartDate && filterEndDate >= taskStartDate) ||
                (filterStartDate <= taskEndDate && filterEndDate >= taskEndDate)) {
                row.style.display = ''; // Show the row
            } else {
                row.style.display = 'none'; // Hide the row
            }
        });
    }
</script>


<script>
    $(document).ready(function() {
        $('#updateUser').on('show.bs.modal', function(event) {
            var button = $(event.relatedTarget);
            var userId = button.data('user_id');
            var userName = button.data('user_name');
            var userEmail = button.data('user_email');
            var modal = $(this);
            modal.find('#user_id').val(userId);
            modal.find('#username2').val(userName);
            modal.find('#useremail').val(userEmail);
        });
    });
</script>


<script>

    let image = document.getElementById("image");
    let input = document.getElementById("profile");

    input.onchange = () => {
        image.src = URL.createObjectURL(input.files[0]);
    }

</script>

</body>
</html>
