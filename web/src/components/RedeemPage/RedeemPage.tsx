import React, {useEffect, useState} from "react";
import {useSearchParams} from "react-router-dom";
import {Api} from "../../index";
import {useForm} from "react-hook-form";
import {Button, Form, FormControl, FormGroup, FormLabel,} from "react-bootstrap";
import {useNavigate} from "react-router";

interface RedeemPageProps {}

interface StatusHolder {
  status: number;
}

function RedeemPageReady(code: string) {
  const [voucherRes, updateVoucherRes] = useState<StatusHolder>();
  useEffect(() => {
    Api.redeemVoucherGet(code).then((res) => {
      updateVoucherRes({ status: res.status });
    });
  }, []);

  return (
    <>
      {voucherRes === undefined ? (
        <p>Loading...</p>
      ) : (
        <>
          {voucherRes?.status === 200 ? (
            <>
              <h3>Successfully applied your voucher</h3>
              <p>Have a greentastic day!</p>
            </>
          ) : (
            <>
              <h3>Failed to redeem your voucher</h3>
              <p>Please check your voucher code</p>
            </>
          )}
        </>
      )}
    </>
  );
}

function RedeemPage() {
  const [searchParams] = useSearchParams();

  const code = searchParams.get("code");

  const navigate = useNavigate();

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();

  if (code !== null) {
    // code is present
    return RedeemPageReady(code);
  } else {
    console.log("No code... Asking for it...");
    return (
      <>
        <h2>Redeem Your Promotion Voucher</h2>
        <Form
          // onSubmit={handleSubmit((res: { code: any; }) => {
          //   navigate({
          //     pathname: "/redeem/",
          //     search: createSearchParams({
          //       code: res.code,
          //     }).toString(),
          //   });
          // })}
        >
          <FormGroup>
            <FormLabel>Voucher Code</FormLabel>
            <FormControl
              {...register("code", { required: true })}
            ></FormControl>
          </FormGroup>
          <FormGroup>
            <Button type="submit" className={"mt-3"}>
              Submit
            </Button>
          </FormGroup>
        </Form>
      </>
    );
  }
}

export default RedeemPage;
