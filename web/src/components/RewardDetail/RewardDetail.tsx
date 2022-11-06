import React, {useEffect, useState} from "react";
import {useNavigate, useParams} from "react-router";
import {Profile, Reward} from "../../openapi";
import {Api} from "../../index";
import {Button, Col, Row} from "react-bootstrap";

interface RewardDetailProps {}

function RewardDetail() {
  let { id } = useParams();
  const reward_id = Number(id);

  const [reward, updateReward] = useState<Reward>();

  // load all elements
  useEffect(() => {
    Api.rewardsGetGet(reward_id).then((res) => {
      updateReward(res.data);
    });
  }, []);

  const [me, updateMe] = useState<Profile>();
  useEffect(() => {
    Api.getMeGet().then((res) => {
      // TODO: get single
      updateMe(res.data);
    });
  }, []);

  const navigate = useNavigate();

  return (
    <>
      <Row>
        <Col className={"col-4"}>
          <img
            src={reward?.picture_url}
            alt={reward?.name}
            style={{ maxWidth: "100%" }}
          />
        </Col>
        <Col>
          <div className="d-flex w-100 justify-content-between">
            <h3 className="mb-1">{reward?.name}</h3>
            <small>
              by {reward?.company_name}{" "}
              <img
                src={reward?.company_avatar_url}
                width={"40rem"}
                alt={reward?.company_name}
              />
            </small>
          </div>
          <p className="mb-1">
            <b style={{ color: "#009E60" }}>
              for {reward?.price_points} points
            </b>
          </p>
          <small className="text-muted">
              <Button
                variant={"primary"}
                disabled={
                  (me?.points ?? -100) < (reward?.price_points ?? 10000000)
                }
                onClick={e => {navigate("/buy_reward/" + reward?.reward_id)}}
              >
                {
                  (me?.points ?? -100) < (reward?.price_points ?? 10000000) ? "Not enough points" : "Claim this reward"
                }
              </Button>
          </small>
        </Col>
      </Row>
      <p>{reward?.description_long}</p>
    </>
  );
}

export default RewardDetail;
