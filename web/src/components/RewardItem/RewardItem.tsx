import React, { FC } from 'react';
import {Link} from "react-router-dom";
import {Col, Row} from "react-bootstrap";
import {Reward} from "../../openapi";


interface RewardItemProps {
  reward: Reward
}

function RewardItem({ reward }: RewardItemProps) {
  return (
    <Link
      to={"/reward/" + reward.reward_id}
      key={reward.reward_id}
      className={'list-group-item list-group-item-action"'}
    >
      <Row>
        <Col className={"col-3"}>
          <img
            src={reward.picture_url}
            alt={reward.name}
            style={{ maxWidth: "100%" }}
          />
        </Col>
        <Col>
          <div className="d-flex w-100 justify-content-between">
            <h5 className="mb-1">{reward.name}</h5>
            <small>
              by {reward.company_name}{" "}
              <img
                src={reward.company_avatar_url}
                width={"20rem"}
                alt={reward.company_name}
              />
            </small>
          </div>
          <p className="mb-1">
            for {reward.price_points} points
          </p>
          <small className="text-muted">{reward.description_short}</small>
        </Col>
      </Row>
    </Link>
  );
}

export default RewardItem;
