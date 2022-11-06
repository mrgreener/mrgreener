import React, { useEffect, useState } from "react";
import { useParams } from "react-router";
import { Profile } from "../../openapi";
import { Api } from "../../index";
import { Col, ProgressBar, Row } from "react-bootstrap";

interface ProfileProps {}

function ProfilePage() {
  let { id } = useParams();

  const [profile, updateProfile] = useState<Profile>();

  // load all elements
  useEffect(() => {
    Api.profilesUsernameGet(id ?? "").then((res) => {
      updateProfile(res.data);
    });
  }, []);

  return (
    <>
      <Row>
        <Col className={"col-3"}>
          <img
            src={profile?.avatar_url}
            className={"rounded-circle"}
            alt={profile?.name}
            style={{ maxWidth: "100%" }}
          />
        </Col>
      </Row>
      <Row>
        <Col>
          <h3 className="mb-1">{profile?.name}</h3>
          <h5 className="mb-1">@{profile?.username}</h5>
        </Col>
      </Row>
      <h5 className={"mt-2"}>Bio</h5>
      <p>{profile?.description}</p>
      <hr />
      <h5>Current status</h5>
      <b>Level {Math.floor(((profile?.points ?? 0) * 10) / 10000)}</b>
      <p>
        Points: <b style={{ color: "#119621" }}>{profile?.points}</b>
      </p>
      <h5>Progress to 10000</h5>
      <ProgressBar
        style={{ height: "40px" }}
        striped
        animated
        variant="success"
        now={((profile?.points ?? 0) * 100) / 10000}
      />
    </>
  );
}

export default ProfilePage;
