import { Button, Checkbox, Col, Form, Input } from "antd";
import axios from "axios";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./login.scss";

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (event) => {
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

        const { role } = user;

        if (role === "ROLE_CUSTOMER") {
          navigate("/");
        } else if (role === "ROLE_ADMIN" || role === "ROLE_SUPER_ADMIN") {
          navigate("/home");
        }
      }
    } catch (error) {
      alert("Tên tài khoản hoặc mật khẩu sai, mời nhập lại");
      console.error("Lỗi khi đăng nhập:", error);
    }
  };
  const onFinish = (values) => {
    console.log("Success:", values);
  };
  const onFinishFailed = (errorInfo) => {
    console.log("Failed:", errorInfo);
  };

  return (
    <div className="login">
      <div className="header">
        <h1 className="title">Đăng nhập</h1>
      </div>
      <div className="body">
        <Col span={8}></Col>
        <Col span={8}>
          <Form
            className="form__login"
            name="basic"
            labelCol={{
              span: 8,
            }}
            wrapperCol={{
              span: 16,
            }}
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
              label="Username"
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
              <Input />
            </Form.Item>

            <Form.Item
              label="Password"
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
              <Input.Password />
            </Form.Item>

            <Form.Item
              name="remember"
              valuePropName="checked"
              wrapperCol={{
                offset: 8,
                span: 16,
              }}
            >
              <Checkbox>Remember me</Checkbox>
            </Form.Item>

            <Form.Item
              wrapperCol={{
                offset: 8,
                span: 16,
              }}
            >
              <Button onClick={handleSubmit} type="primary" htmlType="submit">
                Sign in
              </Button>
            </Form.Item>
          </Form>
        </Col>
        <Col span={8}></Col>
      </div>
    </div>
  );
};

export default Login;
