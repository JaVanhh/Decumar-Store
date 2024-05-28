import { Col, Row } from "antd";
import React from "react";
import "./footer.scss";
import img59 from "../../acssets/images/img59.png";

function Footer() {
  return (
    <div>
      <div className="footer">
        <Row style={{ marginTop: "70px" }}>
          <Col span={4}></Col>
          <Col span={8}>
            <div>
              <img src={img59} alt="" />
            </div>
            <div style={{ marginTop: "40px" }}>
              <p>
                <strong>
                  © 2016 – BẢN QUYỀN THUỘC CÔNG TY CỔ PHẦN DƯỢC MỸ PHẨM CVI
                </strong>
                <br />
                GPKD số 0105440255 do sở KH & ĐT TP Hà Nội cấp ngày 05/08/2011
              </p>
              <p>
                {" "}
                Hotline: <strong>0981237820</strong>
              </p>
              <p>
                <strong> Miền Bắc:</strong> CÔNG TY CỔ PHẦN CVI MIỀN BẮC
              </p>
              <p>
                <strong>Địa chỉ:</strong>Lô đất CN1-08B-3 Khu công nghiệp công
                nghệ cao 1 – Khu công nghệ
                <br />
                cao Hòa Lạc, Km 29 đại lộ Thăng Long, xã Thạch Hòa, huyện Thạch
                Thất, TP.
                <br />
                Hà Nội
              </p>
              <p>
                <strong> Văn phòng: </strong>Tầng 5 -Tòa nhà Comatce Tower –
                Quận Thanh Xuân – Hà Nội
              </p>
              <p>
                {" "}
                <strong> Điện thoại: </strong> 0981237820
              </p>
            </div>
          </Col>
          <Col span={8}>
            <div>
              {" "}
              <h1> icon</h1>{" "}
            </div>
            <div style={{ marginTop: "40px" }}>
              {" "}
              <h3
                style={{
                  fontSize: "18px",
                  fontWeight: "600",
                  textTransform: "uppercase",
                }}
              >
                {" "}
                Thông tin{" "}
              </h3>
              <ul style={{ marginTop: "30px" }}>
                <li>
                  {" "}
                  <a href="?"> Giới Thiệu</a>
                </li>
                <li>
                  {" "}
                  <a href="?">Hướng dẫn mua hàng, thanh toán</a>
                </li>
                <li>
                  {" "}
                  <a href="?">Chính sách đổi, trả hàng</a>
                </li>
                <li>
                  {" "}
                  <a href="?"> Chính sách giao nhận, chuyển hàng</a>
                </li>
                <li>
                  {" "}
                  <a href="?"> Quy chế hoạt động TMĐT</a>
                </li>
                <li>
                  {" "}
                  <a href="?"> Chính sách bảo mật</a>
                </li>
              </ul>
              <h3 style={{ marginTop: "30px" }}> HỎI ĐÁP</h3>
              <ul style={{ marginTop: "30px", marginBottom: "150px" }}>
                <li>
                  {" "}
                  <a href="?"> Câu Hỏi Thường Gặp</a>
                </li>
                <li>
                  {" "}
                  <a href="?"> Clip Hướng Dẫn</a>
                </li>
                <li>
                  {" "}
                  <a href="?"> Tips Skincare</a>
                </li>
              </ul>
            </div>
          </Col>
          <Col span={4}></Col>
        </Row>
      </div>
    </div>
  );
}

export default Footer;
