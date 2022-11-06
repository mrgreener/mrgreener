import {useParams} from "react-router";
import React, {useEffect, useState} from "react";
import {Api} from "../../index";
import PromotionItem from "../PromotionItem/PromotionItem";
import {ListGroup} from "react-bootstrap";

function OrganisationPromotionsPage() {
    let {id} = useParams();
    const [promotions, updatePromotions] = useState([] as JSX.Element[]);

    // load all groups
    useEffect(() => {
        Api.promotionsAllGet(parseInt(id ?? "")).then((res) => {
            const objs = res.data.map((promo) => PromotionItem({promotion: promo}));
            updatePromotions(objs);
        });
    }, []);

    return <ListGroup>{promotions}</ListGroup>;
}

export default OrganisationPromotionsPage;