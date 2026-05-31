import { useState } from "react";

export default function AdminUsers() {
  let [users, setUsers] = useState([]);
  let [showButton, setShowButton] = useState(true);

  let getUsers = async () => {
    let token = localStorage.getItem("token");

    let response = await fetch("http://localhost:8080/admin/users", {
      method: "GET",
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });

    let data = await response.json();
    setUsers(data);
    setShowButton(false);
  };

  let deleteUser = async (id) => {
    let token = localStorage.getItem("token");

    await fetch(`http://localhost:8080/admin/users/${id}`, {
      method: "DELETE",
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });

    alert("User deleted");
    getUsers();
  };

  return (
    <div className="card">
      <h2>All Users</h2>

      {showButton && <button onClick={getUsers}>Load Users</button>}

      <div className="users">
        {users.map((user) => (
          <div className="user-card" key={user.id}>
            <h3>{user.name}</h3>
            <p>{user.email}</p>
            <span>ID: {user.id}</span>

            <button className="delete" onClick={() => deleteUser(user.id)}>
              Delete
            </button>
          </div>
        ))}
      </div>
    </div>
  );
}

