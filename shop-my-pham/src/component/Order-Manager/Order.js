import { Button, Popconfirm, Space, Table } from "antd";
import React from "react";
const User = (props) => {
  const { mydata, handleDeleteUser, showModalUpdate } = props;

  const columns = [
    {
      title: "ID",
      dataIndex: "id",
      key: "id",
    },
    {
      title: "Order Date",
      dataIndex: "order_date",
      key: "order_date",
    },
    {
      title: "Quantity",
      dataIndex: "quantity",
      key: "quantity",
    },
    {
      title: "Total",
      dataIndex: "total",
      key: "total",
    },
    {
      title: "Delivery Fee",
      dataIndex: "delivery_fee",
      key: "delivery_fee",
    },
    {
      title: "Voucher Value",
      dataIndex: "voucher_value",
      key: "voucher_value",
    },
    {
      title: "Shipment Date",
      dataIndex: "shipment_date",
      key: "shipment_date",
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
    ? mydata.map((order) => ({
        id: order.id,
        order_date: order.order_date,
        quantity: order.quantity,
        total: order.total,
        delivery_fee: order.delivery_fee,
        voucher_value: order.voucher_value,
        shipment_date: order.shipment_date,
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
