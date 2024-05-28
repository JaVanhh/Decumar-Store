/* eslint-disable react/jsx-pascal-case */
import { SearchOutlined } from "@ant-design/icons";
import { Button, Col, Input, Modal, Row } from "antd";
import axios from "axios";
import React, { useEffect, useState } from "react";
import Header_Cms from "../Header-Cms/Header_Cms";
import Menu_Cms from "../Menu/Menu_Cms";
import User from "./User";
import "./style.scss";

function User_Manager() {
  const [users, setUsers] = useState();
  const [form, setForm] = useState({
    id: "",
    name: "",
    phone: "",
    username: "",
    email: "",
    password: "",
    avatar_img: "",
  });
  const [modaldata, setmodalData] = useState([]);
  const token = localStorage.getItem("token");
  const username = localStorage.getItem("username");

  useEffect(() => {
    fetchData();
  }, []);

  const handleDeleteUser = (userId) => {
    axios
      .delete(`http://localhost:8080/user/api/delete?userId=${userId}`, {
        headers: {
          token: token,
          username: username,
        },
      })
      .then((response) => {
        alert("xoa thanh cong");
        fetchData();
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const handleChangeValue = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };
  const handleSubmitUpdate = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.put(
        `http://localhost:8080/user/api/update?userId=${modaldata.id}`,
        {
          name: form.name,
          phone: form.phone,
          avatar_img: form.avatar_img,
        },
        {
          headers: {
            token: token,
            username: username,
          },
        }
      );
      alert("cap nhat thanh cong");
      fetchData();
      setIsModalOpenUpdate(false);
      console.log("User updated successfully:", response.data);
    } catch (error) {
      console.error("Error updating user:", error);
    }
  };

  const fetchData = async () => {
    try {
      const response = await axios.get("http://localhost:8080/user/api/show", {
        headers: {
          token: token,
          username: username,
        },
      });

      setUsers(response.data);
    } catch (error) {
      console.error("Error fetching data", error);
    }
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    const newData = form;
    axios
      .post("http://localhost:8080/user/api/add", newData, {
        headers: {
          token: token,
          username: username,
        },
      })
      .then((response) => {
        setForm([...users, response.data.content]);
        alert("them thanh cong");

        fetchData();
        setForm({
          id: "",
          name: "",
          phone: "",
          username: "",
          email: "",
          password: "",
          avatar_img: "",
        });
        setIsModalOpen(false);
      })
      .catch((error) => {
        console.error("Error adding new data:", error);
      });
  };

  const handleChange = (event) => {
    setForm({
      ...form,
      [event.target.name]: event.target.value,
    });
  };

  //modal add user
  const [isModalOpen, setIsModalOpen] = useState(false);
  const showModal = () => {
    setIsModalOpen(true);
  };
  const handleCancel = () => {
    setIsModalOpen(false);
  };

  //modal update user
  const [isModalOpenUpdate, setIsModalOpenUpdate] = useState(false);

  //CHECK CHECK -------------
  const showModalUpdate = (user) => {
    setIsModalOpenUpdate(true);

    setmodalData(user);
  };

  const handleCancelUpdate = () => {
    setIsModalOpenUpdate(false);
  };

  //input seach
  return (
    <div className="user__manager">
      <div className="header">
        <Header_Cms />
      </div>
      <div className="body">
        <Row>
          <Col span={4} className="menu">
            <Menu_Cms />
          </Col>
          <Col span={20} className="content">
            <div className="child__content">
              <div>
                <Row>
                  <Col span={12} style={{ marginRight: "10px" }}>
                    <h1 className="title"> USER MANAGER</h1>
                    <Input
                      addonBefore={<SearchOutlined />}
                      placeholder="seach"
                    />
                  </Col>
                  <Col span={4}></Col>
                  <Col span={4}></Col>
                </Row>
              </div>

              <Modal
                title="Add User"
                open={isModalOpen}
                onOk={handleSubmit}
                onCancel={handleCancel}
              >
                <form>
                  <input
                    type="text"
                    placeholder="Avatar"
                    name="avatar_img"
                    value={form.avatar_img}
                    onChange={handleChange}
                    className="input"
                    style={{
                      marginBottom: "10px",
                      marginRight: "50px",
                      height: "25px",
                    }}
                  />
                  <input
                    type="text"
                    placeholder="Name"
                    name="name"
                    value={form.name}
                    onChange={handleChange}
                    className="input"
                    style={{
                      marginBottom: "10px",
                      marginRight: "50px",
                      height: "25px",
                    }}
                  />
                  <input
                    type="number"
                    placeholder="Phone"
                    name="phone"
                    value={form.phone}
                    onChange={handleChange}
                    className="input"
                    style={{
                      marginBottom: "10px",
                      marginRight: "50px",
                      height: "25px",
                    }}
                  />
                  <input
                    type="text"
                    placeholder="Username"
                    name="username"
                    value={form.username}
                    onChange={handleChange}
                    className="input"
                    style={{
                      marginBottom: "10px",
                      marginRight: "50px",
                      height: "25px",
                    }}
                  />
                  <input
                    type="text"
                    placeholder="Email"
                    name="email"
                    value={form.email}
                    onChange={handleChange}
                    className="input"
                    style={{
                      marginBottom: "10px",
                      marginRight: "50px",
                      height: "25px",
                    }}
                  />
                  <input
                    type="text"
                    placeholder="Password"
                    name="password"
                    value={form.password}
                    onChange={handleChange}
                    className="input"
                    style={{
                      marginBottom: "10px",
                      marginRight: "50px",
                      height: "25px",
                    }}
                  />
                </form>
              </Modal>
              <Modal
                title="Update User"
                open={isModalOpenUpdate}
                onCancel={handleCancelUpdate}
                onOk={handleSubmitUpdate}
              >
                <form>
                  <input
                    type="text"
                    name="userId"
                    placeholder="ID"
                    value={modaldata.id}
                    style={{
                      marginBottom: "10px",
                      marginRight: "50px",
                      height: "25px",
                    }}
                  />
                  <input
                    type="text"
                    name="name"
                    placeholder={modaldata.name}
                    value={form.name}
                    onChange={handleChangeValue}
                    style={{
                      marginBottom: "10px",
                      marginRight: "50px",
                      height: "25px",
                    }}
                  />
                  <input
                    type="text"
                    name="phone"
                    placeholder={modaldata.phone}
                    value={form.phone}
                    onChange={handleChangeValue}
                    style={{
                      marginBottom: "10px",
                      marginRight: "50px",
                      height: "25px",
                    }}
                  />
                  <input
                    type="text"
                    name="username"
                    placeholder="Username"
                    value={modaldata.username}
                    onChange={handleChangeValue}
                    style={{
                      marginBottom: "10px",
                      marginRight: "50px",
                      height: "25px",
                    }}
                  />
                  <input
                    type="text"
                    name="email"
                    placeholder="Email"
                    value={modaldata.email}
                    onChange={handleChangeValue}
                    style={{
                      marginBottom: "10px",
                      marginRight: "50px",
                      height: "25px",
                    }}
                  />
                  <input
                    type="text"
                    name="avatar_img"
                    placeholder="Avatar"
                    value={form.avatar_img}
                    onChange={handleChangeValue}
                    style={{
                      marginBottom: "10px",
                      marginRight: "50px",
                      height: "25px",
                    }}
                  />
                </form>
              </Modal>

              <User
                mydata={users}
                fetchData={fetchData}
                showModalUpdate={showModalUpdate}
                handleDeleteUser={handleDeleteUser}
              />
              <Button
                type="primary"
                onClick={showModal}
                style={{ marginBottom: "20px" }}
              >
                {" "}
                Add User
              </Button>
            </div>
          </Col>
        </Row>
      </div>
    </div>
  );
}

export default User_Manager;
