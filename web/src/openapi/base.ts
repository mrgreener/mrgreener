/* tslint:disable */
/* eslint-disable */
/**
 * MrGreener
 * MrGreener
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


import { Configuration } from "./configuration";
// Some imports not used depending on template conditions
// @ts-ignore
import globalAxios, { AxiosPromise, AxiosInstance, AxiosRequestConfig } from 'axios';

export const BASE_PATH = "http://localhost/api/v1".replace(/\/+$/, "");

/**
 *
 * @export
 */
export const COLLECTION_FORMATS = {
    csv: ",",
    ssv: " ",
    tsv: "\t",
    pipes: "|",
};

/**
 *
 * @export
 * @interface RequestArgs
 */
export interface RequestArgs {
    url: string;
    options: AxiosRequestConfig;
}

/**
 *
 * @export
 * @class BaseAPI
 */
export class BaseAPI {
    protected configuration: Configuration | undefined;

    constructor(configuration?: Configuration, protected basePath: string = BASE_PATH, protected axios: AxiosInstance = globalAxios) {
        if (configuration) {
            this.configuration = configuration;
            this.basePath = configuration.basePath || this.basePath;
        }
    }
};

/**
 *
 * @export
 * @class RequiredError
 * @extends {Error}
 */
export class RequiredError extends Error {
    name: "RequiredError" = "RequiredError";
    constructor(public field: string, msg?: string) {
        super(msg);
    }
}
