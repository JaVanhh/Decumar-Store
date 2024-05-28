/* eslint-disable react/jsx-pascal-case */
import { SearchOutlined } from "@ant-design/icons";
import { Button, Col, Input, Modal, Row } from "antd";
import axios from "axios";
import React, { useEffect, useState } from "react";
import Menu_Cms from "../Menu/Menu_Cms";
import Product from "./Product";
import "./style.scss";
import Header_Cms from "../Header-Cms/Header_Cms";

function Product_Manager() {
  const [product, setProduct] = useState();
  const [form, setForm] = useState({
    id: "",
    name: "",
    quantityStock: "",
    productPrice: "",
    categoryId: "",
  });
  const [modaldata, setmodalData] = useState([]);
  const token = localStorage.getItem("token");
  const username = localStorage.getItem("username");
  const [keyword, setKeyword] = useState("");

  useEffect(() => {
    fetchData();
  }, []);

  const handleDeleteUser = (productId) => {
    axios
      .delete(`http://localhost:8080/product/api/delete/${productId}`, {
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
        `http://localhost:8080/product/api/update/${modaldata.id}`,
        {
          id: "",
          name: "",
          quantityStock: "",
          productPrice: "",
          categoryId: "",
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
        "http://localhost:8080/product/api/show",
        {
          headers: {
            token: token,
            username: username,
          },
        }
      );
      setProduct(response.data);
    } catch (error) {
      console.error("Error fetching data", error);
    }
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    const newData = form;

    axios
      .post("http://localhost:8080/product/api/add", newData, {
        headers: {
          token: token,
          username: username,
        },
      })
      .then((response) => {
        setForm([...product, response.data.content]);
        alert("them thanh cong");

        fetchData();
        setForm({
          id: "",
          name: "",
          quantityStock: "",
          productPrice: "",
          categoryId: "",
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
  const handleSearch = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8080/product/api/searchbyname?keyword=${keyword}`
      );
      setProduct(response.data);
    } catch (error) {
      console.error("There was an error fetching the data!", error);
    }
  };

  // Handle keyword change
  const handleKeywordChange = (e) => {
    const newKeyword = e.target.value;
    setKeyword(newKeyword);
    handleSearch(newKeyword);
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
                    <h1 className="title"> PRODUCT MANAGER</h1>
                    <Input
                      value={keyword}
                      onChange={handleKeywordChange}
                      addonBefore={<SearchOutlined onClick={handleSearch} />}
                      placeholder="seach"
                    />
                  </Col>
                  <Col span={4}></Col>
                  <Col span={4}></Col>
                </Row>
              </div>

              <Modal
                title="Add Product"
                open={isModalOpen}
                onOk={handleSubmit}
                onCancel={handleCancel}
              >
                <form>
                  <input
                    type="text"
                    placeholder="Image"
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
                    placeholder="Quantity Stock"
                    name="quantityStock"
                    value={form.quantityStock}
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
                    placeholder="Product Price"
                    name="productPrice"
                    value={form.productPrice}
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
                    placeholder="Category Id"
                    name="categoryId"
                    value={form.categoryId}
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
                title="Update Product"
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
                    type="number"
                    name="quantityStock"
                    placeholder={modaldata.quantityStock}
                    value={form.quantityStock}
                    onChange={handleChangeValue}
                    style={{
                      marginBottom: "10px",
                      marginRight: "50px",
                      height: "25px",
                    }}
                  />
                  <input
                    type="number"
                    name="productPrice"
                    placeholder={modaldata.productPrice}
                    value={form.productPrice}
                    onChange={handleChangeValue}
                    style={{
                      marginBottom: "10px",
                      marginRight: "50px",
                      height: "25px",
                    }}
                  />
                  <input
                    type="text"
                    name="categoryId"
                    placeholder={modaldata.categoryId}
                    value={modaldata.categoryId}
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

              <Product
                mydata={product}
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
                Add Product
              </Button>
            </div>
          </Col>
        </Row>
      </div>
    </div>
  );
}

export default Product_Manager;
