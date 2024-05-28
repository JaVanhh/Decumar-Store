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
      title: "Product Quantity",
      dataIndex: "productQuantity",
      key: "productQuantity",
      showSorterTooltip: { target: "full-header" },
      sorter: (a, b) => a.productQuantity - b.productQuantity,
      sortDirections: ["ascend"],
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
    ? mydata.map((category) => ({
        id: category.id,
        image: category.image,
        name: category.name,
        productQuantity: category.productQuantity,
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
