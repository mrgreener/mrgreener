import React from "react";
import { Promotion } from "../../openapi";
import { Col, Row } from "react-bootstrap";
import { Link } from "react-router-dom";

interface PromotionItemProps {
  promotion: Promotion;
}

function PromotionItem({ promotion }: PromotionItemProps) {
  return (
    <Link
      to={"/promotion/" + promotion.promotion_id}
      key={promotion.picture_url + promotion.name}
      className={'list-group-item list-group-item-action"'}
    >
      <Row>
        <Col className={"col-3"}>
          <img
            src={promotion.picture_url}
            alt={promotion.name}
            style={{ maxWidth: "100%" }}
          />
        </Col>
        <Col>
          <div className="d-flex w-100 justify-content-between">
            <h5 className="mb-1">{promotion.name}</h5>
            <small>
              by {promotion.company_name}{" "}
              <img
                src={promotion.company_avatar_url}
                width={"20rem"}
                alt={promotion.company_name}
              />
            </small>
          </div>
          <p className="mb-1">
            {" "}
            {promotion.price_string} for {promotion.reward_points} points
          </p>
          <small className="text-muted">{promotion.description_short}</small>
        </Col>
      </Row>
    </Link>
  );
}

export default PromotionItem;
