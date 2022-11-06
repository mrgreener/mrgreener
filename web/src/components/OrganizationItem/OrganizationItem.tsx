import React from "react";
import {Organization} from "../../openapi";
import {Col, Row} from "react-bootstrap";
import {Link} from "react-router-dom";

interface OrganizationItemProps {
    organization: Organization;
    isRewards: Boolean;
}

function OrganizationItem({ organization,  isRewards }: OrganizationItemProps) {
    return (
        <Link
            to={"/organization/" + organization.organization_id + (isRewards ? "/rewards" : "/promotions")}
            key={organization.avatar_url + organization.name}
            className={'list-group-item list-group-item-action"'}
        >
            <Row>
                <Col className={"col-3"}>
                    <img
                        src={organization.avatar_url}
                        alt={organization.name}
                        style={{ maxWidth: "100%" }}
                    />
                </Col>
                <Col>
                    <div className="d-flex w-100 justify-content-between">
                        <h5 className="mb-1">{organization.name}</h5>
                    </div>
                    <small className="text-muted">{organization.description}</small>
                </Col>
            </Row>
        </Link>
    );
}

export default OrganizationItem;
