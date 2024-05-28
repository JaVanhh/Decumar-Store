import { MinusOutlined, PlusOutlined } from "@ant-design/icons";
import { Button, Col, Row, Tabs } from "antd";
import "owl.carousel/dist/assets/owl.carousel.css";
import "owl.carousel/dist/assets/owl.theme.default.css";
import React, { useState } from "react";
import OwlCarousel from "react-owl-carousel";
import img50 from "../acssets/images/img50.png";
import img51 from "../acssets/images/img51.jpeg";
import img52 from "../acssets/images/img52.jpeg";
import img53 from "../acssets/images/img53.jpeg";
import img54 from "../acssets/images/img54.jpg";
import img55 from "../acssets/images/img55.png";
import img56 from "../acssets/images/img56.png";
import img57 from "../../acssets/images/img57.png";
import img58 from "../acssets/images/img58.png";
import Footer from "../Layout/Footer/Footer";
import Header from "../Layout/Header/Header";
import Uses from "../Uses/Uses";
import "./ProductDetail.scss";
import Ingredient from "../Ingredient/Ingredient";
import UserManual from "../UserManual/UserManual";

function ProductDetail() {
  const [data, setData] = useState([]);
  const [quantity, setQuantity] = useState(1);
  const [price, setPrice] = useState(105);
  const [id, setId] = useState(1);

  const addToCart = (product, quantity, price, salePrice) => {
    const productInCart = data.find((item) => item.id === product.id);
    if (productInCart) {
      const updatedCart = data.map((item) =>
        item.id === product.id
          ? {
              ...item,
              quantity: item.quantity + quantity,
              price: price * (item.quantity + quantity),
              salePrice: salePrice * (item.quantity + quantity),
            }
          : item
      );
      setData(updatedCart);
      setQuantity(1);
    } else {
      setData([
        ...data,
        {
          ...product,
          quantity,
          price: price * quantity,
          salePrice: salePrice * quantity,
        },
      ]);
      setQuantity(1);
    }
  };
  const handle1Click = () => {
    setId(1);
    setPrice(105);
  };
  const deleteItem = (id) => {
    let currentData = data;
    currentData = currentData.filter((item) => item.id !== id);
    setData(currentData);
  };
  let newItem = {
    id: id,
    title:
      "Gel ngừa mụn Decumar NANO THC (Nghệ không màu) 20g , kháng khuẩn, chống viêm, ngừa thâm sẹo",
    price: price,
  };
  const updatePlus = (id) => {
    let productInCart = data.find((item) => item.id === id);
    if (productInCart) {
      const updatedCart = data.map((item) =>
        item.id === id
          ? {
              ...item,
              quantity: item.quantity + quantity,
              price: price * (item.quantity + quantity),
            }
          : item
      );
      setData(updatedCart);
      setQuantity(1);
    }
  };
  const updateMinus = (id) => {
    let productInCart = data.find((item) => item.id === id);
    if (productInCart.quantity > 1) {
      const updatedCart = data.map((item) =>
        item.id === id
          ? {
              ...item,
              quantity: item.quantity - quantity,
              price: price * (item.quantity - quantity),
            }
          : item
      );
      setData(updatedCart);
      setQuantity(1);
    }
  };

  const onChange = (key) => {
    console.log(key);
  };
  const items = [
    {
      key: "1",
      label: "Công dụng",
      children: <Uses />,
    },
    {
      key: "2",
      label: "Thành phần chính",
      children: <Ingredient />,
    },
    {
      key: "3",
      label: "Hướng dẫn sử dụng",
      children: <UserManual />,
    },
  ];

  const handleMinus = () => {
    if (quantity > 1) {
      setQuantity(quantity - 1);
    }
  };
  const handlepPlus = () => {
    setQuantity(quantity + 1);
  };

  const [openCart, setOpenCart] = useState(false);
  const [placementCart] = useState("right");
  const showDrawerCart = () => {
    setOpenCart(true);
  };
  const onCloseCart = () => {
    setOpenCart(false);
  };

  return (
    <div>
      <Header
        data={data}
        quantity={quantity}
        price={price}
        openCart={openCart}
        placementCart={placementCart}
        showDrawerCart={showDrawerCart}
        onCloseCart={onCloseCart}
        deleteItem={deleteItem}
        updatePlus={updatePlus}
        updateMinus={updateMinus}
        quantityInCart={quantity}
      />
      <div className="ProductDetail__content">
        <Row>
          <Col span={4}></Col>
          <Col span={8} style={{ marginRight: "15px" }}>
            <OwlCarousel className="owl-theme" loop margin={10} nav items={1}>
              <div class="item">
                <img src={img50} alt="" />
              </div>
              <div class="item">
                <img src={img51} alt="" />
              </div>
              <div class="item">
                <img src={img52} alt="" />
              </div>
              <div class="item">
                <img src={img53} alt="" />
              </div>
            </OwlCarousel>
          </Col>
          <Col span={8} style={{ marginLeft: "15px" }}>
            <div className="product__form">
              <div className="product__title">
                <p>
                  {" "}
                  Gel ngừa mụn Decumar NANO THC (Nghệ không màu) 20g , kháng
                  khuẩn, chống viêm, ngừa thâm sẹo
                </p>
              </div>
              <div className="product__price">
                <span>105,000₫ / tuýp 20g</span>
              </div>
              <div className="product__description">
                <p style={{ marginTop: "20px" }}>
                  DECUMAR – THƯƠNG HIỆU CHĂM SÓC DA MỤN TỪ THẢO DƯỢC CÔNG NGHỆ
                  CAO
                </p>
                <p style={{ marginTop: "20px" }}>
                  Gel ngừa mụn DECUMAR Nano THC với các thành phần ưu việt giúp:
                </p>
                <ul style={{ marginLeft: "50px" }}>
                  <li>Kháng viêm, kháng khuẩn,</li>
                  <li>Làm dịu, làm xẹp nốt mụn nhanh từ 2 – 3 ngày</li>
                  <li>Hạn chế tình trạng thâm, sẹo sau mụn</li>
                </ul>
                <p style={{ marginTop: "20px" }}>
                  Đặc biệt, Gel ngừa mụn DECUMAR Nano THC sở hữu SIÊU NGHỆ NANO
                  KHÔNG MÀU THC mang tính thẩm mỹ cao kết hợp với chiết xuất lá
                  chanh sim & lá Neem giúp tăng hiệu quả chống viêm – xử lý mụn
                  thâm sẹo nhanh chóng. Gel ngừa mụn DECUMAR Nano THC là vũ khí
                  trị mụn vô hình bạn nên sở hữu.
                </p>
              </div>
              <div style={{ marginTop: "20px" }}>
                {" "}
                <img width={"100%"} src={img54} alt="" />
              </div>
              <div className="options" style={{ marginTop: "40px" }}>
                <Row gutter={[16, 16]}>
                  <Col span={8}>
                    <div className="product__quantity">
                      <Button
                        onClick={() => {
                          handleMinus();
                        }}
                        className="button__minus"
                      >
                        <MinusOutlined />
                      </Button>
                      <input
                        type="text"
                        value={quantity}
                        className="form__control"
                      />
                      <Button
                        onClick={() => {
                          handlepPlus();
                        }}
                        className="button__plus"
                      >
                        <PlusOutlined />
                      </Button>
                    </div>
                  </Col>
                  <Col span={8}>
                    <div className="add__to__cart">
                      {" "}
                      <button
                        type="button"
                        className="button__add__to__card"
                        onClick={() => {
                          showDrawerCart();
                          addToCart(newItem, quantity, price);
                          handle1Click();
                        }}
                      >
                        {" "}
                        Thêm Vào Giỏ{" "}
                      </button>
                    </div>
                  </Col>
                  <Col span={8}>
                    <div className="buy__it__now">
                      <button className="button__buy__it__now" type="button">
                        {" "}
                        Mua Ngay
                      </button>
                    </div>
                  </Col>
                </Row>
              </div>
              <hr style={{ marginTop: "40px" }} />
              <div className="introduce">
                <Tabs
                  defaultActiveKey="1"
                  items={items}
                  onChange={onChange}
                  indicatorSize={(origin) => origin - 16}
                />
              </div>
              <div className="recommended__products">
                {" "}
                <h4> Sản phẩm đề xuất</h4>
                <div>
                  <OwlCarousel
                    className="owl-theme"
                    loop
                    margin={10}
                    nav
                    dots={false}
                  >
                    <div class="item">
                      <img src={img55} alt="" />
                    </div>
                    <div class="item">
                      <img src={img56} alt="" />
                    </div>
                    <div class="item">
                      <img src={img57} alt="" />
                    </div>
                    <div class="item">
                      <img src={img58} alt="" />
                    </div>
                  </OwlCarousel>
                </div>
              </div>
            </div>
          </Col>
          <Col span={4}></Col>
        </Row>
      </div>
      <Footer />
    </div>
  );
}

export default ProductDetail;
