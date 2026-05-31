import { useState } from "react";

export default function Login({ setIsLoggedIn }) {
  let [email, setEmail] = useState("admin@gmail.com");
  let [password, setPassword] = useState("admin123");

  let login = async () => {
    let response = await fetch(
      "https://rest-api-1kcb.onrender.com/auth/login",
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ email, password }),
      },
    );

    let data = await response.json();

    if (data.token) {
      localStorage.setItem("token", data.token);
      setIsLoggedIn(true);
      alert("Login successful");
    } else {
      alert("Login failed");
    }
  };

  return (
    <div className="card">
      <h2>Login</h2>

      <input
        type="email"
        value={email}
        placeholder="Enter email"
        onChange={(e) => setEmail(e.target.value)}
      />

      <input
        type="password"
        value={password}
        placeholder="Enter password"
        onChange={(e) => setPassword(e.target.value)}
      />

      <button onClick={login}>Login</button>
    </div>
  );
}
