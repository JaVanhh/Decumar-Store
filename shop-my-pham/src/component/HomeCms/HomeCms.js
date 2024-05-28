/* eslint-disable react/jsx-pascal-case */
import { Col, Row } from "antd";
import React from "react";
import Header_Cms from "../Header-Cms/Header_Cms";
import Menu_Cms from "../Menu/Menu_Cms";
import "./homecms.scss";

function Home() {
  return (
    <div className="home">
      <div className="header">
        <Header_Cms />
      </div>
      <div>
        <Row>
          <Col span={4} className="menu">
            <Menu_Cms />
          </Col>
          <Col span={20}></Col>
        </Row>
      </div>
    </div>
  );
}

export default Home;
