/* eslint-disable react/jsx-pascal-case */
import { SearchOutlined } from "@ant-design/icons";
import { Button, Col, Input, Modal, Row } from "antd";
import axios from "axios";
import React, { useEffect, useState } from "react";
import Menu_Cms from "../Menu/Menu_Cms";
import Category from "./Category";
import "./style.scss";
import Header_Cms from "../Header-Cms/Header_Cms";

function Category_Manager() {
  const [categorys, setCategorys] = useState();
  const [form, setForm] = useState({
    id: "",
    image: "",
    name: "",
    productQuantity: "",
  });
  const [modaldata, setmodalData] = useState([]);
  const token = localStorage.getItem("token");
  const username = localStorage.getItem("username");

  useEffect(() => {
    fetchData();
  }, []);

  const handleDeleteUser = (categoryId) => {
    axios
      .delete(`http://localhost:8080/category/api/delete/${categoryId}`, {
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
        `http://localhost:8080/category/api/update/${modaldata.id}`,
        {
          name: form.name,
          phone: form.phone,
          username: form.username,
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
      const response = await axios.get(
        "http://localhost:8080/category/api/show",
        {
          headers: {
            token: token,
            username: username,
          },
        }
      );
      setCategorys(response.data);
    } catch (error) {
      console.error("Error fetching data", error);
    }
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    const newData = form;

    axios
      .post("http://localhost:8080/category/api/add", newData, {
        headers: {
          token: token,
          username: username,
        },
      })
      .then((response) => {
        setForm([...categorys, response.data.content]);
        alert("them thanh cong");

        fetchData();
        setForm({
          id: "",
          image: "",
          name: "",
          productQuantity: "",
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
                    <h1 className="title"> CATEGORY MANAGER</h1>
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
                title="Add Category"
                open={isModalOpen}
                onOk={handleSubmit}
                onCancel={handleCancel}
              >
                <form>
                  <input
                    type="text"
                    placeholder="Image"
                    name="image"
                    value={form.image}
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
                    placeholder="Product Quantity"
                    name="productQuantity"
                    value={form.productQuantity}
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
                title="Update Category"
                open={isModalOpenUpdate}
                onCancel={handleCancelUpdate}
                onOk={handleSubmitUpdate}
              >
                <form>
                  <input
                    type="text"
                    name="id"
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
                    name="image"
                    placeholder="Image"
                    value={form.image}
                    onChange={handleChangeValue}
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
                </form>
              </Modal>

              <Category
                mydata={categorys}
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
                Add Category
              </Button>
            </div>
          </Col>
        </Row>
      </div>
    </div>
  );
}

export default Category_Manager;
