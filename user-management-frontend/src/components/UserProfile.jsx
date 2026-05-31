import { useState } from "react";

export default function UserProfile() {
  let [profile, setProfile] = useState(null);
  let [showButton, setShowButton] = useState(true);

  let getProfile = async () => {
    let token = localStorage.getItem("token");

    let response = await fetch(
      "https://rest-api-1kcb.onrender.com/user/profile",
      {
        method: "GET",
        headers: {
          Authorization: `Bearer ${token}`,
        },
      },
    );

    let data = await response.json();

    setProfile(data);
    setShowButton(false);
  };

  return (
    <div className="card">
      <h2>User Profile</h2>

      {showButton && <button onClick={getProfile}>Get Profile</button>}

      {profile && (
        <div className="profile">
          <h3>{profile.name}</h3>
          <p>{profile.email}</p>
          <span>ID: {profile.id}</span>
        </div>
      )}
    </div>
  );
}
