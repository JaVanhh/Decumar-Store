import { MinusOutlined, PlusOutlined } from "@ant-design/icons";
import { Button, Col, Row, Popconfirm } from "antd";
import React from "react";
import img50 from "../acssets/images/img50.png";
import "./Cart.scss";

function Cart(props) {
  const { data, deleteItem, updatePlus, updateMinus } = props;

  const handleMinus = (id) => {
    updateMinus(id);
  };
  const handlePlus = (id) => {
    updatePlus(id);
  };
  const handleDeleteProduct = (id) => {
    deleteItem(id);
  };
  const totalPrice = () => {
    var total = 0;
    for (let i = 0; i < data.length; i++) {
      total = total + data[i].price;
    }
    return total;
  };
  const shipping = () => {
    var total = 0;
    var shipping = 0;
    for (let i = 0; i < data.length; i++) {
      total = total + data[i].price;
      if (total < 250) {
        shipping = 18;
      } else {
        shipping = 0;
      }
    }
    return shipping;
  };
  const total = () => {
    var total = totalPrice() + shipping();
    return total;
  };
  return (
    <div>
      <div className="cart__container">
        {data.map((data) => {
          return (
            <>
              <div style={{ marginTop: "20px" }}>
                <Row>
                  <Col span={7}>
                    <div>
                      <img
                        style={{
                          width: "100%",
                          border: "1px solid black",
                          borderRadius: "6px",
                        }}
                        src={img50}
                        alt=""
                      />
                    </div>
                  </Col>
                  <Col span={17}>
                    <Row style={{ marginLeft: "20px" }}>
                      <Col span={18}>
                        <div style={{ fontWeight: "bold", fontSize: "15px" }}>
                          {" "}
                          {data.title}
                        </div>
                      </Col>
                      <Col span={6}>
                        {" "}
                        <div
                          style={{
                            fontSize: "15px",
                            color: "red",
                            textDecoration: "underline",
                            paddingLeft: "14px",
                          }}
                        >
                          <Popconfirm
                            style={{ textAlign: "center" }}
                            description="Are you sure ?"
                            okText=<button
                              style={{
                                border: "none",
                                background: "none",
                                color: "white",
                                width: "100%",
                                height: "100%",
                                marginInlineStart: "0px",
                                cursor: "pointer",
                              }}
                              onClick={() => {
                                handleDeleteProduct(data.id);
                              }}
                            >
                              Yes
                            </button>
                            cancelText="No"
                          >
                            {" "}
                            Remove
                          </Popconfirm>
                        </div>
                      </Col>
                    </Row>
                    <Row style={{ marginLeft: "20px", marginTop: "20px" }}>
                      <Col span={18}>
                        <div className="quantity__in__cart">
                          <Button
                            onClick={() => handleMinus(data.id)}
                            className="button__minus"
                          >
                            <MinusOutlined />
                          </Button>
                          <input
                            type="text"
                            value={data.quantity}
                            className="form__control"
                          />
                          <Button
                            onClick={() => handlePlus(data.id)}
                            className="button__plus"
                          >
                            <PlusOutlined />
                          </Button>
                        </div>
                      </Col>
                      <Col span={6}>
                        <div style={{ fontSize: "17px",marginTop:"6px" }}>
                          {data.price},000đ
                        </div>
                      </Col>
                    </Row>
                  </Col>
                </Row>
              </div>
              <hr style={{ marginTop: "20px" }} />
            </>
          );
        })}
      </div>
      <div
        className="cart-bottom"
        style={{ backgroundColor: "#eceff4", borderTop: "1px solid #eceff4" }}
      >
        <div
          style={{
            width: "100%",
            justifyContent: "space-between",
            padding: "10px 10px",
            display: "flex",
          }}
        >
          {" "}
          Subtotal{" "}
          <span style={{ paddingRight: "20px" }}> {totalPrice()},000đ</span>
        </div>
        <div
          style={{
            width: "100%",
            justifyContent: "space-between",
            padding: "10px 10px",
            display: "flex",
          }}
        >
          {" "}
          Shipping
          <span style={{ paddingRight: "20px" }}>{shipping()},000đ</span>
        </div>
        <div
          style={{
            width: "100%",
            justifyContent: "space-between",
            padding: "10px 10px",
            display: "flex",
          }}
        >
          {" "}
          Total <span style={{ paddingRight: "20px" }}>
            {total()},000đ{" "}
          </span>{" "}
        </div>
      </div>
    </div>
  );
}

export default Cart;
