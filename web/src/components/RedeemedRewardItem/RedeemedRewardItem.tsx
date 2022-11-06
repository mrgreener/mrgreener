import React from "react";
import {RewardVoucher} from "../../openapi";
import {Col, Row} from "react-bootstrap";
import {Link} from "react-router-dom";

interface RedeemedRewardProps {
    redeemedRewards: RewardVoucher;
}

function RedeemedRewardItem({ redeemedRewards }: RedeemedRewardProps) {
    return (
        <Link
            to={"/redeemed_rewards/" + redeemedRewards.id}
            key={redeemedRewards.content}
            className={'list-group-item list-group-item-action"'}
        >
            <Row>
                <Col className={"col-3"}>
                    <p>{redeemedRewards.content}</p>
                </Col>
                <Col>
                    <p>{redeemedRewards.issuedOn}</p>
                </Col>
            </Row>
        </Link>
    );
}

export default RedeemedRewardItem;
