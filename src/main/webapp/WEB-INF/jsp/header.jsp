<%--
  Created by IntelliJ IDEA.
  User: anzel
  Date: 16.02.2020
  Time: 21:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="css/all.css">
    <link rel="stylesheet" href="css/style.css">

    <link href="https://fonts.googleapis.com/css?family=Roboto&display=swap" rel="stylesheet">
    <title>Title</title>
</head>
<body>
<div class="container">
    <nav>
        <ul class = "main-menu">
            <li class="main-menu__item"><a href="#"><i class="fas fa-boxes"></i>Товары</a>
                <ul class="inner-menu cargo-menu">
                    <li class="inner-menu__item cargo-menu__item">
                        <a>
                            <form class="form-header" action="<%=request.getContextPath() + "/getallcargo"%>" method="post">
                                <input class="input-header" type="submit" value="все товары"/>
                            </form>
                        </a>
                    </li>
                    <li class="inner-menu__item cargo-menu__item">
                        <a>найти по id</a>
                    </li>
                    <li class="inner-menu__item cargo-menu__item">
                        <a>найти по имени</a>
                    </li>
                    <li class="inner-menu__item cargo-menu__item">
                        <a>удалить</a>
                    </li>
                    <li class="inner-menu__item cargo-menu__item">
                        <a>сортировать</a>
                    </li>
                    <li class="inner-menu__item cargo-menu__item">
                        <a>добавить</a>
                    </li>
                </ul>
            </li>
            <li class="main-menu__item"><a href="#"><i class="fas fa-people-carry"></i>Поставщики</a>
                <ul class="inner-menu carrier-menu">
                    <li class="inner-menu__item carrier-menu__item">
                        <a>
                            <form class="form-header" action="" method="post">
                                <input class="input-header" type="submit" value="все поставщики"/>
                            </form>
                        </a>
                    </li>
                    <li class="inner-menu__item carrier-menu__item">
                        <a>найти по id</a>
                    </li>
                    <li class="inner-menu__item carrier-menu__item">
                        <a>найти по имени</a>
                    </li>
                    <li class="inner-menu__item carrier-menu__item">
                        <a>удалить</a>
                    </li>
                    <li class="inner-menu__item carrier-menu__item">
                        <a>добавить</a>
                    </li>
                </ul>
            </li>
            <li class="main-menu__item"><a href="#"><i class="fas fa-dolly-flatbed"></i>Транспортировки</a>
                <ul class="inner-menu transportation-menu">
                    <li class="inner-menu__item transportation-menu__item">
                        <a>
                            <form class="form-header" action="" method="post">
                                <input class="input-header" type="submit" value="все транспортировки"/>
                            </form>
                        </a>
                    </li>
                    <li class="inner-menu__item transportation-menu__item">
                        <a>найти по id</a>
                    </li>
                    <li class="inner-menu__item transportation-menu__item">
                        <a>удалить</a>
                    </li>
                    <li class="inner-menu__item transportation-menu__item">
                        <a>добавить</a>
                    </li>
                </ul>
            </li>
        </ul>

    </nav>
</div>

</body>
</html>
