import React from "react";
import { Route, Link } from "react-router-dom";
import styled, { css } from "styled-components";
import * as utils from "../utils";
function Header() {
  return (
    <S.Navbar>
      <S.Logo>
        <Link to={utils.URL.MAIN.MAIN} style={{ textDecoration: "none" }}>
          mialuce
        </Link>
      </S.Logo>
      
      <S.Menu>
        <li>
          <Link to={utils.URL.BOARD.MAIN} style={{ textDecoration: "none" }}>
            자유게시판
          </Link>
        </li>
        <li>
          <Link to={utils.URL.RECORD.MAIN} style={{ textDecoration: "none" }}>
            운동기록
          </Link>
        </li>
        <li>
          <Link to={utils.URL.PLAN.MAIN} style={{ textDecoration: "none" }}>
            하루계획
          </Link>
        </li>
        <li>
          <Link to={utils.URL.MARKET.MAIN} style={{ textDecoration: "none" }}>
            장터
          </Link>
        </li>
        <li>
          <Link to={utils.URL.PLAY.MAIN} style={{ textDecoration: "none" }}>
            게임
          </Link>
        </li>
      </S.Menu>
      <S.Login>로그인</S.Login>
    </S.Navbar>
  );
}

const S = {
  Logo: styled.div``,
  Navbar: styled.div``,
  Menu: styled.ul``,
  Login: styled.div``,
};

export default Header;
