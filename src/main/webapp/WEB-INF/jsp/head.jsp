<%--
  Created by IntelliJ IDEA.
  User: anzel
  Date: 16.02.2020
  Time: 21:03
  To change this template use File | Settings | File Templates.
--%>
<header class="container">
    <nav>
        <ul class = "main-menu">
            <li class="main-menu__item"><a href="#"><i class="fas fa-boxes"></i>Cargos</a>
                <ul class="inner-menu cargo-menu">
                    <li class="inner-menu__item cargo-menu__item">
                        <a>
                            <form class="form-header" action="<%=request.getContextPath() + "/cargos"%>" method="post">
                                <input class="input-header" type="submit" value="all cargos"/>
                            </form>
                        </a>
                    </li>
                    <li class="inner-menu__item cargo-menu__item">
                        <a>find by id</a>
                    </li>
                    <li class="inner-menu__item cargo-menu__item">
                        <a>find by name</a>
                    </li>
                    <li class="inner-menu__item cargo-menu__item">
                        <a>delete by id</a>
                    </li>
                    <li class="inner-menu__item cargo-menu__item">
                        <a>sort</a>
                    </li>
                    <li class="inner-menu__item cargo-menu__item">
                        <a>add</a>
                    </li>
                </ul>
            </li>
            <li class="main-menu__item"><a href="#"><i class="fas fa-people-carry"></i>Carriers</a>
                <ul class="inner-menu carrier-menu">
                    <li class="inner-menu__item carrier-menu__item">
                        <a>
                            <form class="form-header" action="" method="post">
                                <input class="input-header" type="submit" value="all carrriers"/>
                            </form>
                        </a>
                    </li>
                    <li class="inner-menu__item carrier-menu__item">
                        <a>find by id</a>
                    </li>
                    <li class="inner-menu__item carrier-menu__item">
                        <a>find by name</a>
                    </li>
                    <li class="inner-menu__item carrier-menu__item">
                        <a>delete by id</a>
                    </li>
                    <li class="inner-menu__item carrier-menu__item">
                        <a>add</a>
                    </li>
                </ul>
            </li>
            <li class="main-menu__item"><a href="#"><i class="fas fa-dolly-flatbed"></i>Transportations</a>
                <ul class="inner-menu transportation-menu">
                    <li class="inner-menu__item transportation-menu__item">
                        <a>
                            <form class="form-header" action="" method="post">
                                <input class="input-header" type="submit" value="all transportations"/>
                            </form>
                        </a>
                    </li>
                    <li class="inner-menu__item transportation-menu__item">
                        <a>find by id</a>
                    </li>
                    <li class="inner-menu__item transportation-menu__item">
                        <a>delete by id</a>
                    </li>
                    <li class="inner-menu__item transportation-menu__item">
                        <a>add</a>
                    </li>
                </ul>
            </li>
        </ul>

    </nav>

    <form class="form" action="<%=request.getContextPath() + "/findbyid"%>" method="post">
        <p>Find Cargo by id</p>
        <input name="cargoid" placeholder="enter id">
        <input class="input" type="submit" value="SELECT"/>
    </form>

    <form class="form" action="<%=request.getContextPath() + "/findbyname"%>" method="post">
        <p>Find Cargo by name</p>
        <input name="cargoname" placeholder="enter name">
        <input class="input" type="submit" value="SELECT"/>
    </form>

    <form class="form" action="<%=request.getContextPath() + "/deletecargobyid"%>" method="post">
        <p>Delete Cargo by id</p>
        <input name="cargoid" placeholder="enter id">
        <input class="input" type="submit" value="SELECT"/>
    </form>

</header>

