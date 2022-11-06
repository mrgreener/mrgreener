import React, {useEffect, useState} from "react";
import {Promotion} from "../../openapi";
import {useParams} from "react-router";
import {Api} from "../../index";
import {Button, Col, Row} from "react-bootstrap";

interface PromotionDetailProps {}

function PromotionDetail() {
  let { id } = useParams();
  const promotion_id = Number(id);

  const [promotion, updatePromotion] = useState<Promotion>();

  // load all elements
  useEffect(() => {
    Api.promotionsGetGet(promotion_id).then((res) => {
      updatePromotion(res.data);
    });
  }, []);
  return (
    <>
      <Row>
        <Col className={"col-4"}>
          <img
            src={promotion?.picture_url}
            alt={promotion?.name}
            style={{ maxWidth: "100%" }}
          />
        </Col>
        <Col>
          <div className="d-flex w-100 justify-content-between">
            <h3 className="mb-1">{promotion?.name}</h3>
            <small>
              by {promotion?.company_name}{" "}
              <img
                src={promotion?.company_avatar_url}
                width={"40rem"}
                alt={promotion?.company_name}
              />
            </small>
          </div>
          <p className="mb-1">
            <b style={{ color: "#009E60" }}>
              +{promotion?.reward_points} points{" "}
            </b>{" "}
          </p>
          <small className="text-muted">
            {promotion?.link_to_store !== undefined ? (
              <a href={promotion?.link_to_store} hidden={true}>
                <Button variant={"primary"}>Use the Deal</Button>
              </a>
            ) : (
              <>{promotion?.where_to_get}</>
            )}
          </small>
        </Col>
      </Row>
      <p>{promotion?.description_long}</p>
    </>
  );
}

export default PromotionDetail;
