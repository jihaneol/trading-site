
import "./App.css";
import styled, { css } from "styled-components";
import Header from "./layout/Header";
import {Routes,Route } from "react-router-dom";
import * as pages from "./pages";
import * as utils from "./utils";
function App() {
  return (
    <div className="App">
     <Header></Header>
        <Routes>
          <Route path={utils.URL.MAIN.MAIN} element={<pages.Main/>}/>
          <Route path={utils.URL.BOARD.MAIN} element={<pages.Board/>}/>
          <Route path={utils.URL.BOARD.DETAIL} element={<pages.DetailBoard/>}/>
          <Route path={utils.URL.MARKET.MAIN} element={<pages.Market/>}/>
          <Route path={utils.URL.PLAY.MAIN} element={<pages.Play/>}/>
          <Route path={utils.URL.PLAN.MAIN} element={<pages.Plan/>}/>
          <Route path={utils.URL.RECORD.MAIN} element={<pages.Record/>}/>
        </Routes>
      
    </div>
  );
}
const S = {
  MainContent: styled.div`
    width: "50px";
    font-size: "50px";
  `,
};
const AppWrapper = styled.div`
  display: flex;
  min-height: 100vh;
  height: 100%;
`;
const MobileWrapper = styled.div`
  min-height: 100vh;
  height: 100%;
`;
export default App;
