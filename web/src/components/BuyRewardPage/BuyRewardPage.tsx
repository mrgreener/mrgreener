import React, { useEffect, useState } from "react";
import { Api } from "../../index";
import { useParams } from "react-router";
import { RewardVoucher } from "../../openapi";

interface BuyRewardPageProps {}

function BuyRewardPage() {
  const { id } = useParams();
  const reward_id = Number(id);

  const [voucher, updateVoucher] = useState<RewardVoucher>();

  // load all elements
  useEffect(() => {
    Api.buyRewardRewardIdPost(reward_id).then((res) => {
      updateVoucher(res.data);
    });
  }, []);
  return (
    <>
      <h2>Claim Your Reward Voucher</h2>
      {voucher === undefined ? (
        <p>Loading...</p>
      ) : (
        <>
          <h2>Congratulations</h2>
          <p>
            Here is your code for the reward: <b>{voucher?.content}</b>
          </p>
          <p>Issued at {voucher?.issuedOn}</p>
        </>
      )}
    </>
  );
}

export default BuyRewardPage;
