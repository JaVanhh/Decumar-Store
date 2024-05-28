import { Button, Popconfirm, Space, Table } from "antd";
import React from "react";
const User = (props) => {
  const { mydata, handleDeleteUser, showModalUpdate } = props;

  const columns = [
    {
      title: "Image",
      dataIndex: "image",
      key: "image",
    },
    {
      title: "Name",
      dataIndex: "name",
      key: "name",
      showSorterTooltip: { target: "full-header" },
      sorter: (a, b) => a.name.length - b.name.length,
      sortDirections: ["ascend"],
    },
    {
      title: "Quantity Stock",
      dataIndex: "quantityStock",
      key: "quantityStock",
    },
    {
      title: "Product Price",
      dataIndex: "productPrice",
      key: "productPrice",
      showSorterTooltip: { target: "full-header" },
      sorter: (a, b) => a.productPrice - b.productPrice,
      sortDirections: ["ascend"],
    },
    {
      title: "Category Id",
      dataIndex: "categoryId",
      key: "categoryId",
    },
    {
      title: "Action",
      key: "action",
      render: (_, record) => (
        <Space size="middle">
          <Button type="primary" Button onClick={() => showModalUpdate(record)}>
            {" "}
            Update{" "}
          </Button>
          <Popconfirm
            onConfirm={() => handleDeleteUser(record.id)}
            style={{ textAlign: "center" }}
            description="Are you sure ?"
            okText="Yes"
            cancelText="No"
          >
            <Button type="primary"> Delete </Button>
          </Popconfirm>
        </Space>
      ),
    },
  ];

  const data = mydata
    ? mydata.map((product) => ({
        id: product.id,
        name: product.name,
        quantityStock: product.quantityStock,
        productPrice: product.productPrice,
        categoryId: product.categoryId,
      }))
    : [];

  return (
    <div className="user-container">
      <div className="user-table mt-3 mx-1">
        <Table
          style={{ marginTop: "20px" }}
          columns={columns}
          dataSource={data}
        />
      </div>
    </div>
  );
};

export default User;
