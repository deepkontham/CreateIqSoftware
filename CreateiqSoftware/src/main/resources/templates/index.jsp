<!DOCTYPE html>
<html  lang="en" xmlns:th="http://www.thymeleaf.org" >

<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0">

    <link rel="stylesheet" type="text/css" th:href="@{/webjars/bootstrap/css/bootstrap.min.css}">

    <script type="text/javascript" th:src="@{/webjars/jquery/jquery.min.js}"></script>
    <script type="text/javascript" th:src="@{/webjars/bootstrap/js/bootstrap.min.js}"></script>

    <title>Home-SAIPATEL Deals Admin</title>
    <style type="text/css">
        img {
           max-height: 50px;
           max-width: 100px;
           padding: 0px;
        }
    </style>

</head>

<body>


    <div class="container-fluid-xl">

        <div>
            <nav class="navbar navbar-expand-lg bg-dark navbar-dark">
                <a class="navbar-brand" th:href="@{/}">
                    <img alt="Saipatel shopping Logo" th:src="@{/images/saipatelshopmelogo.jpg}">
                </a>

                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#topNavbar">
                    <span class="navbar-toggler-icon"> </span>
                </button>

                <div class="collapse navbar-collapse" id="topNavbar">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link" th:href="@{/users}">Users</a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" th:href="@{/categories}">Categories</a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" th:href="@{/brands}">Brands</a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" th:href="@{/products}">Products</a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" th:href="@{/customers}">Customers</a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" th:href="@{/shippings}">Shippings</a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" th:href="@{/orders}">Orders</a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" th:href="@{/reports}">Sales Report</a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" th:href="@{/articles}">Articles</a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" th:href="@{/menus}">Menus</a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" th:href="@{/settings}">Settings</a>
                        </li>

                    </ul>
                </div>
            </nav>
        </div>


        <h1>Welcome to SAI Shopme Admin</h1>

    </div>

    <div class="text-center">
        <p>Shopme Control Panel - Copyright &copy; Shopme</p>
    </div>

</body>

</html>
