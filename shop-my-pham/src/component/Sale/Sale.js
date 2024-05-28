import React from "react";
import Header from "../../components/Layout/Header/Header";
import Footer from "../Layout/Footer/Footer";
import img42 from "../../acssets/image/img42.jpeg";
import img43 from "../../acssets/image/img43.svg";
import img44 from "../../acssets/image/img44.svg";
import img45 from "../../acssets/image/img45.svg";
import img46 from "../../acssets/image/img46.jpg";
import img47 from "../../acssets/image/img47.png";
import img48 from "../../acssets/image/img48.jpeg";
import img49 from "../../acssets/image/img49.png";
import img50 from "../../acssets/image/img50.jpeg";
import { Row, Col } from "antd";
import "./sale.scss";

function Sale() {
  return (
    <div>
      <Header />
      <div className="sale__content">
        <div className="content__1">
          <img style={{ width: "100%" }} src={img42} alt="" />
        </div>
        <div className="content__2">
          <Row>
            <Col span={5}></Col>
            <Col span={14}>
              <Row>
                <div className="content__2__child">
                  <img src={img43} alt="" /> <span> Làm đẹp </span>
                </div>
                <div className="content__2__child">
                  <img src={img44} alt="" /> <span>Trải nghiệm</span>
                </div>
                <div className="content__2__child">
                  <img src={img45} alt="" /> <span> Tin tức </span>
                </div>
              </Row>
            </Col>
            <Col span={5}></Col>
          </Row>
        </div>
        <div className="content__3">
          <Row>
            <Col span={4}></Col>
            <Col span={3}>
              <div className="content__3__child">
                <img src={img46} alt="" />
                <p>
                  Review decumar có tốt không và những đánh giá của người dùng
                </p>
              </div>
              <div className="content__3__child">
                <img src={img47} alt="" />
                <p>
                  Decumar thc – nghệ không màu, gel trong suốt giảm mụn, thâm,
                  sẹo sau 2 – 3 ngày
                </p>
              </div>
              <div className="content__3__child">
                <img src={img48} alt="" />
                <p>Gi gỉ gì gi mụn gì cũng hết… chỉ cần 1 “liều” decumar!</p>
              </div>
            </Col>
            <Col span={8}>
              <div>
                <img style={{ width: "100%" }} src={img49} alt="" />
                <h2 style={{ marginTop: "25px" }}>
                  {" "}
                  Gel trị mụn decumar: dòng advanced hay thc tốt hơn?{" "}
                </h2>
                <p style={{ marginTop: "25px" }}>
                  Bạn đã biết chưa? Decumar hiện nay có 2 dòng sản phẩm Gel trị
                  mụn khác nhau: dòng Advanced và dòng THC. Cùng tìm hiểu về sự
                  khác biệt 2 dòng này để lựa chọn ra gel trị mụn phù hợp nhé!
                  1. So sánh v…
                </p>
              </div>
            </Col>
            <Col span={5}>
              <div>
                <img
                  style={{ width: "80%", marginLeft: "35px" }}
                  src={img50}
                  alt=""
                />
              </div>
            </Col>
            <Col span={4}></Col>
          </Row>
        </div>
      </div>
      <Footer />
    </div>
  );
}

export default Sale;
