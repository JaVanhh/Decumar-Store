import { Col, Row } from "antd";
import "./Header_Cms.scss";
import logo from "../acssets/images/logo.svg";
import { Link } from "react-router-dom";

function Header_Cms() {
  return (
    <div className="header-main">
      <Row>
        <Col span={4}>
          <Link to="/home">
            <img src={logo} alt="" className="logo" />
          </Link>
        </Col>
        <Col span={14}></Col>
        <Col span={6}>
          <Link to="/">logout</Link>
        </Col>
      </Row>
    </div>
  );
}

export default Header_Cms;
