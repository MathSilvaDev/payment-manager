export interface Page<T>{
  content: T,
  empty: boolean,
  first: boolean,
  last: boolean,
  number: number
}

export interface GetParams<T>{
  pageNumber: number,
  pageSize: number,
  filter: T
}