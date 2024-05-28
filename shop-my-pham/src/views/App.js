/* eslint-disable react/jsx-pascal-case */
import React from "react";
import Home from "../component/Home/Home";
import ProductDetail from "../component/ProductDetail/ProductDetail";
import Login from "../component/Login/Login";
import "./App.scss";
import { Route, Routes } from "react-router";
// import { Route, Routes } from "react-router-dom";
import User_Manager from "../component/User-Manager/User_Manager";
import Category_Manager from "../component/Category-Manager/Category_Manager";
import Product_Manager from "../component/Product-Manager/Product_Manager";
import Order_Manager from "../component/Order-Manager/Order_Manager";
import HomeCms from "../component/HomeCms/HomeCms";
import Account_Manager from "../component/Account/Account_Manager";
// import Login from "../component/Login/Login";
// import ProductDetail from "../component/ProductDetail/ProductDetail";
import Product_List from "../component/Product-List/Product_List";

function App() {
  return (
    <div className="App">
      <Routes>
        <Route path="/" element={<Home />}></Route>
        <Route path="/product-detail" element={<ProductDetail />}></Route>
        <Route path="/login" element={<Login />}></Route>
        <Route path="/home" element={<HomeCms />}></Route>
        <Route path="/users-management" element={<User_Manager />} />
        <Route path="/products-management" element={<Product_Manager />} />
        <Route path="/categories-management" element={<Category_Manager />} />
        <Route path="/orders-management" element={<Order_Manager />} />
        <Route path="/account" element={<Account_Manager />} />
        <Route path="/category/decumar-advanced" element={<Product_List />} />
      </Routes>
    </div>
  );
}

export default App;
