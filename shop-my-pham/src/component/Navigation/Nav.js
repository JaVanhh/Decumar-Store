import React from "react";
import { Link } from "react-router-dom";
import "./style.scss";

function Nav() {
  return (
    <div>
      <ul>
      <li>
          <Link to="/home" className="nav-link">
            Home
          </Link>
        </li>
        <li>
          <Link to="/users-management" className="nav-link">
            User Manager
          </Link>
        </li>
        <li>
          <Link to="/products-management" className="nav-link">
            Product Manager
          </Link>
        </li>
        <li>
          <Link to="/categories-management" className="nav-link">
            Category_Manager
          </Link>
        </li>
        <li>
          <Link to="/orders-management" className="nav-link">
            Order_Manager
          </Link>
        </li>
      </ul>
    </div>
  );
}

export default Nav;
