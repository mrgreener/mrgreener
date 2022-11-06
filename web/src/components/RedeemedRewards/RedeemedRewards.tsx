import React, {useEffect, useState} from "react";
import {Promotion, RewardVoucher} from "../../openapi";
import {Col, ListGroup, Row} from "react-bootstrap";
import { Link } from "react-router-dom";
import {Api} from "../../index";
import RedeemedRewardItem from "../RedeemedRewardItem/RedeemedRewardItem";

interface RedeemedRewardsProps {}

function RedeemedRewards() {
    const [redeemedCodes, updateRedeemedCodes] = useState([] as JSX.Element[]);

    useEffect(() => {
        Api.rewardVouchersMyGet().then((res) => {
            const objs = res.data.map((code) => RedeemedRewardItem({redeemedRewards: code}))
            updateRedeemedCodes(objs);
        })
    }, []);

    return <ListGroup>{redeemedCodes}</ListGroup>;
}

export default RedeemedRewards;
