import { useState } from "react";

export default function CreateUser() {
  let [name, setName] = useState("");
  let [email, setEmail] = useState("");
  let [password, setPassword] = useState("");

  let createUser = async () => {
    let token = localStorage.getItem("token");

    let response = await fetch(
      "https://rest-api-1kcb.onrender.com/admin/users",
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify({ name, email, password }),
      },
    );

    if (response.ok) {
      alert("User created successfully");
      setName("");
      setEmail("");
      setPassword("");
    } else {
      alert("User creation failed");
    }
  };

  return (
    <div className="card">
      <h2>Create User</h2>

      <input
        value={name}
        placeholder="Name"
        onChange={(e) => setName(e.target.value)}
      />

      <input
        value={email}
        placeholder="Email"
        onChange={(e) => setEmail(e.target.value)}
      />

      <input
        value={password}
        type="password"
        placeholder="Password"
        onChange={(e) => setPassword(e.target.value)}
      />

      <button onClick={createUser}>Create User</button>
    </div>
  );
}
