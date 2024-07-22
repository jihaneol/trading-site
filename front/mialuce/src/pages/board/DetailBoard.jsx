import React from "react";
import { useParams } from "react-router-dom";
import { Board } from "./Board";
export const DetailBoard = () => {
  const a = useParams();
  const id = 3;

  return (
    <>
      <div>
        <h1>DetailBoard {id}</h1>
        <div>내용</div>

        <div>
          comment
          <div> 이름,등록일,프로필,내용</div>
        </div>

        <div>
          <h3>댓글을 입력하세요</h3>
          <input type="입력" />
          <button type="button"> 제출</button>
        </div>
      </div>
      <Board></Board>
    </>
  );
};
