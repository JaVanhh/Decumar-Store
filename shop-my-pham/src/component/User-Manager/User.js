import { Button, Popconfirm, Space, Table } from "antd";
import React from "react";


const User = (props) => {
  const { mydata, handleDeleteUser, showModalUpdate } = props;

  const columns = [
    {
      title: "Image",
      dataIndex: "url",
      render: (t, r) => (
        <img
          alt=""
          style={{ width: "100px", height: "120px" }}
          src={`${r.url}`}
        />
      ),
    },
    {
      title: "Name",
      dataIndex: "name",
      key: "name",
      showSorterTooltip: { target: "full-header" },
      sorter: (a, b) => a.name.length - b.name.length,
      sortDirections: ["descend"],
    },
    {
      title: "Phone Number",
      dataIndex: "phone",
      key: "phone",
    },
    {
      title: "Username",
      dataIndex: "username",
      key: "username",
    },
    {
      title: "Email",
      dataIndex: "email",
      key: "email",
    },
    {
      title: "Role",
      dataIndex: "role",
      key: "role",
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
            onConfirm={(event) => handleDeleteUser(record.id)}
            
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
    ? mydata.map((user) => ({
        id: user.id,
        url: user.url,
        name: user.name,
        phone: user.phone,
        username: user.username,
        email: user.email,
        role: user.role,
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
