import { Question } from "./Questions"

export interface Test{
        projectName:string,
        managerName:string,
        auditDetail:{
            auditType:string,
            auditDate:string,
            auditQuestions:Question[]
                
            }
    
}

//   test : Test ={
//     projectName:"",
//     managerName:"",
//     auditDetail:{
//         auditType:"Internal",
//         auditDate:"",
//         auditQuestions:
//             [
//                 {
//                     questionId: 1,
//                     question: "Have all Change requests followed SDLC before PROD move?",
//                     auditType: "Internal",
//                     response: "No"
//                 },
//                 {
//                     questionId: 2,
//                     question: "Have all Change requests been approved by the application owner?",
//                     auditType: "Internal",
//                     response: "No"
//                 },
//                 {
//                     questionId: 3,
//                     question: "Are all artifacts like CR document, Unit test cases available?",
//                     auditType: "Internal",
//                     response: "Yes"
//                 },
//                 {
//                     questionId: 4,
//                     question: "Is the SIT and UAT sign-off available?",
//                     auditType: "Internal",
//                     response: "Yes"
//                 },
//                 {
//                     questionId: 5,
//                     question: "Is data deletion from the system done with application owner approval?",
//                     auditType: "Internal",
//                     response: "Yes"
//                 }
//             ]
//         }

// }