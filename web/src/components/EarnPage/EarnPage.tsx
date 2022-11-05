import React, { useEffect, useState } from "react";
import { Api } from "../../index";
import PromotionItem from "../PromotionItem/PromotionItem";
import { ListGroup } from "react-bootstrap";

interface EarnPageProps {}

function EarnPage() {
  const [promotions, updatePromotions] = useState([] as JSX.Element[]);

  // load all groups
  useEffect(() => {
    Api.promotionsAllGet().then((res) => {
      const objs = res.data.map((promo) => PromotionItem({ promotion: promo }));
      updatePromotions(objs);
    });
  }, []);

  return <ListGroup>{promotions}</ListGroup>;
}

export default EarnPage;
