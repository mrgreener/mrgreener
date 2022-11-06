import React, {useEffect, useState} from "react";
import {Api} from "../../index";
import {ListGroup} from "react-bootstrap";
import OrganizationItem from "../OrganizationItem/OrganizationItem";

interface SpendPageProps {
}

function SpendPage() {
    const [organisations, updateOrganizations] = useState([] as JSX.Element[]);

    // load all groups
    useEffect(() => {
        Api.organizationsAllGet().then((res) => {
            const objs = res.data.map((org) => OrganizationItem({
                organization: org,
                isRewards: true
            }));
            updateOrganizations(objs);
        });
    }, []);

    return <ListGroup>{organisations}</ListGroup>;
}

export default SpendPage;
