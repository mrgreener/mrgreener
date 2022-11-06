import {useParams} from "react-router";
import React, {useEffect, useState} from "react";
import {Api} from "../../index";
import {ListGroup} from "react-bootstrap";
import RewardItem from "../RewardItem/RewardItem";

function OrganisationRewardsPage() {
    let {id} = useParams();
    const [rewards, updateRewards] = useState([] as JSX.Element[]);

    // load all groups
    useEffect(() => {
        Api.rewardsAllGet(parseInt(id ?? "")).then((res) => {
            const objs = res.data.map((rew) => RewardItem({reward: rew}));
            updateRewards(objs);
        });
    }, []);

    return <ListGroup>{rewards}</ListGroup>;
}

export default OrganisationRewardsPage;