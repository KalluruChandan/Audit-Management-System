import { Question } from "./Questions"

export default interface AuditDetail{
    auditType:string
    auditDate:string
    auditQuestion:Question[]
}