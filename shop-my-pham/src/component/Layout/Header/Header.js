import {
  SearchOutlined,
  ShoppingCartOutlined,
  UserOutlined,
} from "@ant-design/icons";
import { Button, Checkbox, Col, Drawer, Form, Input, Row } from "antd";
import axios from "axios";
import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import Cart from "../../Cart/Cart";
import logo from "../../acssets/images/logo.svg";
import "./header.scss";

function Header(props) {
  const {
    data,
    placementCart,
    showDrawerCart,
    onCloseCart,
    openCart,
    deleteItem,
    updatePlus,
    updateMinus,
   handleSearch
  } = props;
  
  
  
  const [openProduct, setOpenProduct] = useState(false);
  const [placement] = useState("left");
  const showDrawer = () => {
    setOpenProduct(true);
  };
  const onClose = () => {
    setOpenProduct(false);
  };

  const [openUser, setOpenUser] = useState(false);
  const [placementUser] = useState("right");
  const showDrawerUser = () => {
    setOpenUser(true);
  };
  const onCloseUser = () => {
    setOpenUser(false);
  };

  const [openRegister, setOpenRegister] = useState(false);
  const [placementRegister] = useState("right");
  const showDrawerRegister = () => {
    setOpenRegister(true);
  };
  const onCloseRegister = () => {
    setOpenRegister(false);
  };

  const onFinish = (values) => {
    console.log("Success:", values);
  };
  const onFinishFailed = (errorInfo) => {
    console.log("Failed:", errorInfo);
  };

  const [name, setName] = useState("");
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [phone, setPhone] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (password !== confirmPassword) {
      alert("Mật khẩu và xác nhận mật khẩu không khớp.");
      return;
    }

    const user = { name, username, email, password, phone, role: "CUSTOMER" };

    try {
      const response = await axios.post(
        "http://localhost:8080/user/api/register",
        user,
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );

      if (response.status === 200) {
        alert("Đăng ký thành công!");
        onCloseRegister(true);
      }
    } catch (error) {
      if (error.response) {
        alert(error.response.data.message || "Đăng ký thất bại!");
      } else {
        alert("Có lỗi xảy ra. Vui lòng thử lại.");
      }
    }
  };
  const navigate = useNavigate();

  const [isLoginDrawerVisible, setIsLoginDrawerVisible] = useState(
    !localStorage.getItem("token")
  );
  const [isLoggedIn, setIsLoggedIn] = useState(!!localStorage.getItem("token"));

  const handleUserIconClick = () => {
    if (isLoggedIn) {
      // Nếu đã đăng nhập, chuyển hướng tới "/account"
      navigate("/account");
    } else {
      // Nếu chưa đăng nhập, hiển thị Drawer đăng nhập
      showDrawerUser();
    }
  };

  const handleSubmitLogin = async (event) => {
    event.preventDefault();

    try {
      const response = await axios.post(
        `http://localhost:8080/back-end/api/login`,
        {
          username: username,
          password: password,
        }
      );

      if (response.status === 200) {
        const { token, user } = response.data;

        localStorage.setItem("token", token);
        localStorage.setItem("username", username);
        localStorage.setItem("user", JSON.stringify(user));
        setIsLoginDrawerVisible(false);
        navigate("/account");

        setIsLoggedIn(true);

        const { role } = user;

        if (role === "ROLE_CUSTOMER") {
          navigate("/account");
        } else if (role === "ROLE_ADMIN" || role === "ROLE_SUPER_ADMIN") {
          navigate("/home");
        }
      }
    } catch (error) {
      alert("Tên tài khoản hoặc mật khẩu sai, mời nhập lại");
      console.error("Lỗi khi đăng nhập:", error);
    }
  };


  return (
    <div>
      <div className="header__main">
        <div className="header__announcement">
     
          <p>
            {" "}
            Tận hưởng giao hàng miễn phí toàn quốc với hoá đơn từ 250.000 đ +
          </p>
        </div>
        <div className="header__menu">
          <Row>
            <Col span={8}>
              <div className="menu__content">
                <span>
                  <SearchOutlined />
                </span>
                <span onClick={showDrawer}> Sản phẩm </span>
                <Drawer
                  title="Sản phẩm"
                  placement={placement}
                  width={600}
                  onClose={onClose}
                  open={openProduct}
                >
                  {" "}
                  <Link to="/category/decumar-advanced">
                    {" "}
                    <p onClick={handleSearch}> Decumar Advanced</p>{" "}
                  </Link>
                  <a href="?">
                    <p>Decumar THC Không Màu</p>{" "}
                  </a>
                  <a href="?">
                    <p>Decumar ProMax</p>{" "}
                  </a>
                </Drawer>

                <span> Khuyến mãi </span>
                <span> Decumar </span>
                <span> Bài viết </span>
              </div>
            </Col>
            <Col span={8}>
              <div className="menu__content">
                {" "}
                <Link to="/">
                  {" "}
                  <img
                    src={logo}
                    alt="logo"
                    style={{ width: "200px", height: "58px", paddingBottom: "20px"}}
                  />{" "}
                </Link>
              </div>
            </Col>
            <Col span={8}>
              <div className="menu__content">
                <ShoppingCartOutlined
                  style={{ fontSize: "25px" }}
                  onClick={showDrawerCart}
                />
                <Drawer
                  title="Giỏ hàng của bạn"
                  placement={placementCart}
                  width={500}
                  onClose={onCloseCart}
                  open={openCart}
                >
                  <div className="cart__body">
                    <Cart
                      data={data}
                      deleteItem={deleteItem}
                      updatePlus={updatePlus}
                      updateMinus={updateMinus}
                    />
                  </div>
                </Drawer>{" "}
                <span>
                  <UserOutlined
                    style={{ fontSize: "25px" }}
                    onClick={handleUserIconClick}
                  />
                  <Drawer
                    title="Đăng nhập"
                    placement={placementUser}
                    width={480}
                    onClose={onCloseUser}
                    open={openUser && isLoginDrawerVisible}
                    className="drawer-login"
                  >
                    <Form
                      name="login"
                      style={{
                        maxWidth: 600,
                      }}
                      initialValues={{
                        remember: true,
                      }}
                      onFinish={onFinish}
                      onFinishFailed={onFinishFailed}
                      autoComplete="off"
                    >
                      <Form.Item
                        name="username"
                        rules={[
                          {
                            required: true,
                            message: "Please input your username!",
                          },
                        ]}
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                      >
                        <Input
                          placeholder="Email"
                          style={{
                            borderRadius: "0px",
                            height: "44px",
                            width: "430px",
                          }}
                        />
                      </Form.Item>

                      <Form.Item
                        name="password"
                        rules={[
                          {
                            required: true,
                            message: "Please input your password!",
                          },
                        ]}
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                      >
                        <Input.Password
                          placeholder="Password"
                          style={{
                            borderRadius: "0px",
                            height: "44px",
                            width: "430px",
                          }}
                        />
                      </Form.Item>

                      <Form.Item name="remember" valuePropName="checked">
                        <Checkbox>Ghi nhớ đăng nhập</Checkbox>
                      </Form.Item>

                      <Form.Item>
                        <Button
                          type="primary"
                          htmlType="submit"
                          onClick={handleSubmitLogin}
                        >
                          Đăng nhập
                        </Button>
                      </Form.Item>
                    </Form>
                    <div className="register">
                      <Button onClick={showDrawerRegister}> Đăng ký </Button>
                      <Drawer
                        title="Tạo tài khoản"
                        placement={placementRegister}
                        width={480}
                        onClose={onCloseRegister}
                        open={openRegister}
                      >
                        <Form
                          name="register"
                          style={{
                            maxWidth: 600,
                          }}
                          initialValues={{
                            remember: true,
                          }}
                          onFinish={onFinish}
                          onFinishFailed={onFinishFailed}
                          autoComplete="off"
                        >
                          <Form.Item
                            name="name"
                            rules={[
                              {
                                required: true,
                                message: "Please input your last name!",
                              },
                            ]}
                          >
                            <Input
                              value={name}
                              onChange={(e) => setName(e.target.value)}
                              placeholder="Họ và tên của bạn"
                              style={{
                                borderRadius: "0px",
                                height: "44px",
                                width: "430px",
                              }}
                            />
                          </Form.Item>
                          <Form.Item
                            name="username"
                            rules={[
                              {
                                required: true,
                                message: "Please input your username!",
                              },
                            ]}
                          >
                            <Input
                              value={username}
                              onChange={(e) => setUsername(e.target.value)}
                              placeholder="Username"
                              style={{
                                borderRadius: "0px",
                                height: "44px",
                                width: "430px",
                              }}
                            />
                          </Form.Item>
                          <Form.Item
                            name="email"
                            rules={[
                              {
                                required: true,
                                message: "Please input your email!",
                              },
                            ]}
                          >
                            <Input
                              value={email}
                              onChange={(e) => setEmail(e.target.value)}
                              placeholder="Email"
                              style={{
                                borderRadius: "0px",
                                height: "44px",
                                width: "430px",
                              }}
                            />
                          </Form.Item>

                          <Form.Item
                            name="password"
                            rules={[
                              {
                                required: true,
                                message: "Please input your password!",
                              },
                            ]}
                          >
                            <Input.Password
                              value={password}
                              onChange={(e) => setPassword(e.target.value)}
                              placeholder="Mật khẩu"
                              style={{
                                borderRadius: "0px",
                                height: "44px",
                                width: "430px",
                              }}
                            />
                          </Form.Item>
                          <Form.Item
                            name="confirmPassword"
                            rules={[
                              {
                                required: true,
                                message: "Please input your confirm password!",
                              },
                            ]}
                          >
                            <Input.Password
                              value={confirmPassword}
                              onChange={(e) =>
                                setConfirmPassword(e.target.value)
                              }
                              placeholder="Xác nhận mật khẩu"
                              style={{
                                borderRadius: "0px",
                                height: "44px",
                                width: "430px",
                              }}
                            />
                          </Form.Item>
                          <Form.Item
                            name="phone"
                            rules={[
                              {
                                required: true,
                                message: "Please input your phone!",
                              },
                            ]}
                          >
                            <Input
                              value={phone}
                              onChange={(e) => setPhone(e.target.value)}
                              placeholder="Số điện thoại"
                              style={{
                                borderRadius: "0px",
                                height: "44px",
                                width: "430px",
                              }}
                            />
                          </Form.Item>
                          <Form.Item>
                            <Button
                              type="primary"
                              htmlType="submit"
                              onClick={handleSubmit}
                            >
                              Đăng ký
                            </Button>
                          </Form.Item>
                        </Form>
                        <div>
                          <span>Đã có tài khoản ?</span>
                          <Button onClick={onCloseRegister}>Đăng nhập</Button>
                        </div>
                      </Drawer>
                    </div>
                  </Drawer>
                </span>
              </div>
            </Col>
          </Row>
        </div>
      </div>
    </div>
  );
}

export default Header;
