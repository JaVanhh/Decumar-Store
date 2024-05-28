import axios from "axios";
import React, { useEffect, useState } from "react";
import Header from "../Layout/Header/Header";
import "./ProductList.scss";
import { Card, List } from "antd";

function Product_List() {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    handleSearch();
  }, []);
  const data = products
    ? products.map((products) => ({
        productPrice: products.productPrice,
        name: products.name,
      }))
    : [];

  const handleSearch = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8080/product/api/searchbycategory?number=${1}`
      );
      setProducts(response.data);
      console.log("check", response.data);
    } catch (error) {
      console.error("There was an error fetching the data!", error);
    }
  };
  return (
    <div>
      <div>
        <Header handleSearch={handleSearch} />
      </div>
      <div style={{padding : "50px 100px"}}>
        <h2 style={{marginBottom: "10px"}}>Decumar Advanced</h2>

        <List
          grid={{
            gutter: 16,
            column: 4,
          }}
          dataSource={data}
          renderItem={(item) => (
            <List.Item>
              <Card>
                {item.name} <br></br> {item.productPrice}
              </Card>
            </List.Item>
          )}
        />
      </div>
    </div>
  );
}

export default Product_List;
