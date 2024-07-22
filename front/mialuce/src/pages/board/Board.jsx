import React from "react";
import styled, { css } from "styled-components";
import { Link } from "react-router-dom";
export const Board = () => {
  const S = {
    Boardlist: styled.div`
      margin: 10px;
    `,
    Head: styled.div`
      margin: 100px;

      border-bottom: 1px solid red;
    `,
    Body: styled.li`
      display: flex;
    `,
  };
  return (
    <S.Boardlist>
      <h1>자유 게시판</h1>
      <S.Head>
        <span>순번</span>
        <span>제목</span>
        <span>등록자명</span>
        <span>등록일</span>
        <span>조회수</span>
      </S.Head>

      <ul style={{ listStyle: "none" }}>
        <Link to={`/board/${1}`}>
          <S.Body>
            <div>1</div>
            <div>테스트 중</div>
            <div>관리자</div>
            <div>오늘</div>
            <div>100</div>
          </S.Body>
        </Link>

        <S.Body>
          <div>2</div>
          <div>테스트 중</div>
          <div>관리자</div>
          <div>오늘</div>
          <div>100</div>
        </S.Body>
      </ul>
    </S.Boardlist>
  );
};
