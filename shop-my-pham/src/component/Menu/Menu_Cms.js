import React from "react";
import {
  UserOutlined,
  ProductOutlined,
  UnorderedListOutlined,
  HomeOutlined,
} from "@ant-design/icons";
import { Menu } from "antd";
import { Link } from "react-router-dom";

function Menu_Cms() {
  return (
    <div>
      <Menu>
        <Link to="/home">
          <Menu.Item
            key={"sub1"}
            style={{
              width: 317,
              border: 1,
            }}
          >
            <span>
              {" "}
              <HomeOutlined /> Dash Board
            </span>{" "}
          </Menu.Item>
        </Link>
        <Link to="/users-management">
          <Menu.Item
            key={"sub2"}
            style={{
              width: 317,
              border: 1,
            }}
            mode="inline"
          >
            <span>
              {" "}
              <UserOutlined /> User Management
            </span>{" "}
          </Menu.Item>
        </Link>
        <Link to="/products-management">
          <Menu.Item
            key={"sub3"}
            style={{
              width: 317,
              border: 1,
            }}
          >
            <span>
              {" "}
              <ProductOutlined /> Product Management
            </span>{" "}
          </Menu.Item>
        </Link>
        <Link to="/categories-management">
          <Menu.Item
            key={"sub4"}
            style={{
              width: 317,
              border: 1,
            }}
            // items={items}
          >
            <span>
              {" "}
              <UnorderedListOutlined /> Category Management
            </span>{" "}
          </Menu.Item>
        </Link>
        <Link to="/orders-management">
          <Menu.Item
            key={"sub5"}
            style={{
              width: 317,
              border: 1,
            }}
            // items={items}
          >
            <span>
              {" "}
              <UnorderedListOutlined /> Order Management
            </span>{" "}
          </Menu.Item>
        </Link>
      </Menu>
    </div>
  );
}

export default Menu_Cms;
