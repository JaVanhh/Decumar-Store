import React from "react";
import Header from "../Layout/Header/Header";
import { Row, Col } from "antd";
import { Menu } from "antd";
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import {
  UserOutlined,
  ShoppingCartOutlined,
  LogoutOutlined,
} from "@ant-design/icons";

function Account_Manager() {
  const user = JSON.parse(localStorage.getItem("user"));
  const navigate = useNavigate();

  const handleLogout = () => {
    // Xóa token khỏi local storage
    localStorage.removeItem("token");
    localStorage.removeItem("username");
    localStorage.removeItem("user");

    // Chuyển hướng về trang chủ
    navigate("/");
    console.log("check count");
  };
  return (
    <div>
      <div>
        <Header />
      </div>
      <div>
        <Row
          style={{
            paddingTop: "50px",
            paddingBottom: "50px",
            background: "#F5F6F9",
            height: "517px",
          }}
        >
          <Col span={4}></Col>
          <Col span={3} style={{ backgroundColor: "white" }}>
            <Menu>
              <Link to="/account">
                <Menu.Item
                  key={"sub1"}
                  style={{
                    width: 230,
                    border: 1,
                  }}
                >
                  <span>
                    {" "}
                    <UserOutlined /> Thông tin
                  </span>{" "}
                </Menu.Item>
              </Link>
              <Link to="/orders">
                <Menu.Item
                  key={"sub2"}
                  style={{
                    width: 230,
                    border: 1,
                  }}
                  mode="inline"
                >
                  <span>
                    {" "}
                    <ShoppingCartOutlined /> Quản lý đơn hàng
                  </span>{" "}
                </Menu.Item>
              </Link>
              <Link to="/address">
                <Menu.Item
                  key={"sub3"}
                  style={{
                    width: 230,
                    border: 1,
                  }}
                >
                  <span>
                    {" "}
                    <UserOutlined /> Địa chỉ
                  </span>{" "}
                </Menu.Item>
              </Link>
              <Menu.Item
                key={"sub4"}
                style={{
                  width: 230,
                  border: 1,
                }}
                onClick={handleLogout}
              >
                <span>
                  {" "}
                  <LogoutOutlined /> Đăng xuất
                </span>{" "}
              </Menu.Item>
            </Menu>
          </Col>
          <Col span={1}></Col>
          <Col
            span={12}
            style={{ backgroundColor: "white", padding: "30px 30px" }}
          >
            <h2>Thông tin tài khoản</h2>
            <p style={{ margin: "50px 0px" }}>
              <span>Username</span>{" "}
              <span style={{ marginLeft: "375px" }}>{user.username}</span>
            </p>
            <p style={{ margin: "50px 0px" }}>
              <span>Name</span>{" "}
              <span style={{ marginLeft: "400px" }}>{user.name}</span>
            </p>
            <p style={{ margin: "50px 0px" }}>
              <span>Email</span>{" "}
              <span style={{ marginLeft: "405px" }}>{user.email}</span>
            </p>
            <p style={{ margin: "50px 0px" }}>
              <span>Phone</span>{" "}
              <span style={{ marginLeft: "400px" }}>{user.phone}</span>
            </p>
          </Col>
          <Col span={4}></Col>
        </Row>
      </div>
    </div>
  );
}

export default Account_Manager;
