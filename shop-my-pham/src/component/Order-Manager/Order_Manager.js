/* eslint-disable react/jsx-pascal-case */
import { SearchOutlined } from "@ant-design/icons";
import { Button, Col, Input, Modal, Row } from "antd";
import axios from "axios";
import React, { useEffect, useState } from "react";
import Menu_Cms from "../Menu/Menu_Cms";
import Order from "./Order";
import "./style.scss";
import Header_Cms from "../Header-Cms/Header_Cms";

function Order_Manager() {
  const [orders, setOrders] = useState();
  const [form, setForm] = useState({
    id: "",
    order_date: "",
    quantity: "",
    total: "",
    delivery_fee: "",
    voucher_value: "",
    shipment_date: "",
  });
  const [modaldata, setmodalData] = useState([]);
  const token = localStorage.getItem("token");
  const username = localStorage.getItem("username");

  useEffect(() => {
    fetchData();
  }, []);

  const handleDeleteUser = (orderId) => {
    axios
      .delete(`http://localhost:8080/order/api/delete/${orderId}`, {
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
        `http://localhost:8080/order/api/update/${modaldata.id}`,
        {
          order_date: form.order_date,
          quantity: form.quantity,
          total: form.total,
          delivery_fee: form.delivery_fee,
          voucher_value: form.voucher_value,
          shipment_date: form.shipment_date,
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
      const response = await axios.get("http://localhost:8080/order/api/show", {
        headers: {
          token: token,
          username: username,
        },
      });
      setOrders(response.data);
    } catch (error) {
      console.error("Error fetching data", error);
    }
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    const newData = form;

    axios
      .post("http://localhost:8080/order/api/add", newData, {
        headers: {
          token: token,
          username: username,
        },
      })
      .then((response) => {
        setForm([...orders, response.data.content]);
        alert("them thanh cong");

        fetchData();
        setForm({
          id: "",
          order_date: "",
          quantity: "",
          total: "",
          delivery_fee: "",
          voucher_value: "",
          shipment_date: "",
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
                    <h1 className="title"> ORDER MANAGER</h1>
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
                title="Add Order"
                open={isModalOpen}
                onOk={handleSubmit}
                onCancel={handleCancel}
              >
                <form>
                  <input
                    type="text"
                    placeholder="Order Date"
                    name="order_date"
                    value={form.order_date}
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
                    placeholder="Quantity"
                    name="quantity"
                    value={form.quantity}
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
                    placeholder="Total"
                    name="total"
                    value={form.total}
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
                    placeholder="Delivery Fee"
                    name="delivery_fee"
                    value={form.delivery_fee}
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
                    placeholder="Voucher Value"
                    name="voucher_value"
                    value={form.voucher_value}
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
                    placeholder="Shipment Date"
                    name="shipment_date"
                    value={form.shipment_date}
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
                title="Update Order"
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
                    name="order_date"
                    placeholder={modaldata.order_date}
                    value={form.order_date}
                    onChange={handleChangeValue}
                    style={{
                      marginBottom: "10px",
                      marginRight: "50px",
                      height: "25px",
                    }}
                  />
                  <input
                    type="number"
                    name="quantity"
                    placeholder={modaldata.quantity}
                    value={form.quantity}
                    onChange={handleChangeValue}
                    style={{
                      marginBottom: "10px",
                      marginRight: "50px",
                      height: "25px",
                    }}
                  />
                  <input
                    type="number"
                    name="total"
                    placeholder={modaldata.total}
                    value={form.total}
                    onChange={handleChangeValue}
                    style={{
                      marginBottom: "10px",
                      marginRight: "50px",
                      height: "25px",
                    }}
                  />
                  <input
                    type="number"
                    name="delivery_fee"
                    placeholder={modaldata.delivery_fee}
                    value={form.delivery_fee}
                    onChange={handleChangeValue}
                    style={{
                      marginBottom: "10px",
                      marginRight: "50px",
                      height: "25px",
                    }}
                  />
                  <input
                    type="number"
                    name="voucher_value"
                    placeholder={modaldata.voucher_value}
                    value={form.voucher_value}
                    onChange={handleChangeValue}
                    style={{
                      marginBottom: "10px",
                      marginRight: "50px",
                      height: "25px",
                    }}
                  />
                  <input
                    type="text"
                    name="shipment_date"
                    placeholder={modaldata.shipment_date}
                    value={form.shipment_date}
                    onChange={handleChangeValue}
                    style={{
                      marginBottom: "10px",
                      marginRight: "50px",
                      height: "25px",
                    }}
                  />
                </form>
              </Modal>

              <Order
                mydata={orders}
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
                Add Order
              </Button>
            </div>
          </Col>
        </Row>
      </div>
    </div>
  );
}

export default Order_Manager;
